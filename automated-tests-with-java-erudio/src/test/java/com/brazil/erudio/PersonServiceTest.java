package com.brazil.erudio;

import com.brazil.erudio.model.Person;
import com.brazil.erudio.service.IPersonService;
import com.brazil.erudio.service.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PersonServiceTest {

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("When Create a Person with Success Should Return a Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldReturnPersonObject() {
        // Given / Arrange
        IPersonService service = new PersonService();

        Person person = new Person(
                "Keith",
                "Moon",
                "kmoon@erudio.com.br",
                "Wembley - UK",
                "Male"
        );

        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        assertNotNull(actual, "The createPerson() should not have returned null!");
    }

}
