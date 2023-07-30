package com.brazil.erudio.integrationtests.controllers;

import com.brazil.erudio.integrationtests.testcontainers.AbstractIntegrationTest;
import com.brazil.erudio.models.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static com.brazil.erudio.configs.TestConfigs.SERVER_PORT;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static io.restassured.filter.log.LogDetail.ALL;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = DEFINED_PORT)
class PersonControllerIntegrationTest extends AbstractIntegrationTest {
    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static Person person;

    @BeforeAll
    void setup() {
        // Given / Arrange
        objectMapper = new ObjectMapper();
        // desabilitando falhar quando a deserializacao encontrar um atributo desconhecido
        objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);

        RequestLoggingFilter requestFilter = new RequestLoggingFilter(ALL);
        ResponseLoggingFilter responseFilter = new ResponseLoggingFilter(ALL);
        specification = new RequestSpecBuilder()
                .setBasePath("")
                .setPort(SERVER_PORT)
                .addFilter(requestFilter)
                .addFilter(responseFilter)
                .build();

        person = new Person();
    }

}
