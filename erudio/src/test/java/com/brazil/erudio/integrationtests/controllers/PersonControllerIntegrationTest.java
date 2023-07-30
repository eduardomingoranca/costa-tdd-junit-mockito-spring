package com.brazil.erudio.integrationtests.controllers;

import com.brazil.erudio.integrationtests.testcontainers.AbstractIntegrationTest;
import com.brazil.erudio.models.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.brazil.erudio.configs.TestConfigs.CONTENT_TYPE_JSON;
import static com.brazil.erudio.configs.TestConfigs.SERVER_PORT;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static io.restassured.RestAssured.given;
import static io.restassured.filter.log.LogDetail.ALL;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = DEFINED_PORT)
class PersonControllerIntegrationTest extends AbstractIntegrationTest {
    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static Person person;

    @BeforeAll
    static void setup() {
        // Given / Arrange
        objectMapper = new ObjectMapper();
        // desabilitando falhar quando a deserializacao encontrar um atributo desconhecido
        objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);

        RequestLoggingFilter requestFilter = new RequestLoggingFilter(ALL);
        ResponseLoggingFilter responseFilter = new ResponseLoggingFilter(ALL);
        specification = new RequestSpecBuilder()
                .setBasePath("/person")
                .setPort(SERVER_PORT)
                    .addFilter(requestFilter)
                    .addFilter(responseFilter)
                .build();

        person = new Person("Colin", "Leech", "Mayfair - London - UK",
                "Male", "colin@erudio.com");
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("integration Given Person Object when Create One Person Should Return A Person Object")
    @Test
    @Order(1)
    void integrationTestGivenPersonObject_when_CreateOnePerson_ShouldReturnAPersonObject() throws JsonProcessingException {
        String content = given().spec(specification)
                .contentType(CONTENT_TYPE_JSON)
                .body(person)
                .when()
                    .post()
                .then()
                    .statusCode(201)
                        .extract()
                            .body()
                                .asString();

        Person createdPerson = objectMapper.readValue(content, Person.class);

        person = createdPerson;

        assertNotNull(createdPerson);

        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());
        assertNotNull(createdPerson.getEmail());

        assertTrue(createdPerson.getId() > 0);
        assertEquals("Colin", createdPerson.getFirstName());
        assertEquals("Leech", createdPerson.getLastName());
        assertEquals("Mayfair - London - UK", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());
        assertEquals("colin@erudio.com", createdPerson.getEmail());
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("integration Given Person Object when Update One Person Should Return A Updated Person Object")
    @Test
    @Order(2)
    void integrationTestGivenPersonObject_when_UpdateOnePerson_ShouldReturnAUpdatedPersonObject() throws JsonProcessingException {
        person.setFirstName("Boaz");
        person.setEmail("boaz@erudio.com");

        String content = given().spec(specification)
                .contentType(CONTENT_TYPE_JSON)
                .body(person)
                .when()
                    .put()
                .then()
                    .statusCode(200)
                        .extract()
                            .body()
                                .asString();

        Person updatedPerson = objectMapper.readValue(content, Person.class);

        person = updatedPerson;

        assertNotNull(updatedPerson);

        assertNotNull(updatedPerson.getId());
        assertNotNull(updatedPerson.getFirstName());
        assertNotNull(updatedPerson.getLastName());
        assertNotNull(updatedPerson.getAddress());
        assertNotNull(updatedPerson.getGender());
        assertNotNull(updatedPerson.getEmail());

        assertTrue(updatedPerson.getId() > 0);
        assertEquals("Boaz", updatedPerson.getFirstName());
        assertEquals("Leech", updatedPerson.getLastName());
        assertEquals("Mayfair - London - UK", updatedPerson.getAddress());
        assertEquals("Male", updatedPerson.getGender());
        assertEquals("boaz@erudio.com", updatedPerson.getEmail());
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("integration Given Person Object when findById Should Return A Person Object")
    @Test
    @Order(3)
    void integrationTestGivenPersonObject_when_findById_ShouldReturnAPersonObject() throws JsonProcessingException {
        String content = given().spec(specification)
                  .pathParam("id", person.getId())
                .when()
                    .get("{id}")
                .then()
                    .statusCode(200)
                        .extract()
                            .body()
                                .asString();

        Person foundPerson = objectMapper.readValue(content, Person.class);

        assertNotNull(foundPerson);

        assertNotNull(foundPerson.getId());
        assertNotNull(foundPerson.getFirstName());
        assertNotNull(foundPerson.getLastName());
        assertNotNull(foundPerson.getAddress());
        assertNotNull(foundPerson.getGender());
        assertNotNull(foundPerson.getEmail());

        assertTrue(foundPerson.getId() > 0);
        assertEquals("Boaz", foundPerson.getFirstName());
        assertEquals("Leech", foundPerson.getLastName());
        assertEquals("Mayfair - London - UK", foundPerson.getAddress());
        assertEquals("Male", foundPerson.getGender());
        assertEquals("boaz@erudio.com", foundPerson.getEmail());
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("integration given person object when find all should return a people list")
    @Test
    @Order(4)
    void integrationTestGivenPersonObject_when_findAll_ShouldReturnAPeopleList() throws JsonProcessingException {
        Person anotherPerson = new Person(
                "Alice",
                "Specter",
                "Mayfair - London - UK",
                "Female",
                "alice@erudio.com");

        given().spec(specification)
                .contentType(CONTENT_TYPE_JSON)
                    .body(anotherPerson)
                .when()
                    .post()
                .then()
                    .statusCode(201);

        String content = given().spec(specification)
                .when()
                    .get()
                .then()
                    .statusCode(200)
                .extract()
                    .body()
                        .asString();

        Person[] peopleArray = objectMapper.readValue(content, Person[].class);
        List<Person> people = asList(peopleArray);

        Person foundPersonOne = people.get(0);

        assertNotNull(foundPersonOne);

        assertNotNull(foundPersonOne.getId());
        assertNotNull(foundPersonOne.getFirstName());
        assertNotNull(foundPersonOne.getLastName());
        assertNotNull(foundPersonOne.getAddress());
        assertNotNull(foundPersonOne.getGender());
        assertNotNull(foundPersonOne.getEmail());

        assertTrue(foundPersonOne.getId() > 0);
        assertEquals("Boaz", foundPersonOne.getFirstName());
        assertEquals("Leech", foundPersonOne.getLastName());
        assertEquals("Mayfair - London - UK", foundPersonOne.getAddress());
        assertEquals("Male", foundPersonOne.getGender());
        assertEquals("boaz@erudio.com", foundPersonOne.getEmail());

        Person foundPersonTwo = people.get(1);

        assertNotNull(foundPersonTwo);

        assertNotNull(foundPersonTwo.getId());
        assertNotNull(foundPersonTwo.getFirstName());
        assertNotNull(foundPersonTwo.getLastName());
        assertNotNull(foundPersonTwo.getAddress());
        assertNotNull(foundPersonTwo.getGender());
        assertNotNull(foundPersonTwo.getEmail());

        assertTrue(foundPersonTwo.getId() > 0);
        assertEquals("Alice", foundPersonTwo.getFirstName());
        assertEquals("Specter", foundPersonTwo.getLastName());
        assertEquals("Mayfair - London - UK", foundPersonTwo.getAddress());
        assertEquals("Female", foundPersonTwo.getGender());
        assertEquals("alice@erudio.com", foundPersonTwo.getEmail());
    }
}
