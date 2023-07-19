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

    @BeforeEach
    void setup() {
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
        // Given / Arrange
        IPersonService service = new PersonService();

        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        assertNotNull(actual, "The createPerson() should not have returned null!");
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("When Create a Person with Success Should Contains Valid Fields in Returned Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainsValidFieldsInReturnedPersonObject() {
        // Given / Arrange
        IPersonService service = new PersonService();

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
        IPersonService service = new PersonService();
        person.setEmail(null);

        // Then / Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                // When / Act
                () -> service.createPerson(person),
                "Empty e-Mail should have cause an IllegalArgumentException");

        String expectedMessage = "The Person e-Mail is null or empty!";

        assertEquals(expectedMessage, exception.getMessage(), "Unexpected exception message!");
    }

}
