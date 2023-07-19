package com.brazil.erudio;

import com.brazil.erudio.model.Person;
import com.brazil.erudio.service.IPersonService;
import com.brazil.erudio.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {
    Person person;
    IPersonService service;

    @BeforeEach
    void setup() {
        service = new PersonService();
        person = new Person(
                "Keith",
                "Moon",
                "kmoon@erudio.com.br",
                "Wembley - UK",
                "Male"
        );
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("When Create a Person with Success Should Return a Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldReturnPersonObject() {
        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        assertNotNull(actual, "The createPerson() should not have returned null!");
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("When Create a Person with Success Should Contains Valid Fields in Returned Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainsValidFieldsInReturnedPersonObject() {
        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        assertNotNull(person.getId(), "Person ID is Missing");
        assertEquals(person.getFirstName(), actual.getFirstName(), "The FirstName is Different!");
        assertEquals(person.getLastName(), actual.getLastName(), "The LastName is Different!");
        assertEquals(person.getAddress(), actual.getAddress(), "The Address is Different!");
        assertEquals(person.getGender(), actual.getGender(), "The Gender is Different!");
        assertEquals(person.getEmail(), actual.getEmail(), "The Email is Different!");
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("When Create a Person with null e-Mail Should throw Exception")
    @Test
    void testCreatePerson_WithNullEMail_ShouldThrowIllegalArgumentException() {
        // Given / Arrange
        person.setEmail(null);

        // When / Act & Then / Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> service.createPerson(person),
                "Empty e-Mail should have cause an IllegalArgumentException");

        String expectedMessage = "The Person e-Mail is null or empty!";

        // Then / Assert
        assertEquals(expectedMessage, exception.getMessage(), "Exception error message is incorrect!");
    }

}
