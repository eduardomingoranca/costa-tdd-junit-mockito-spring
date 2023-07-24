package com.brazil.erudio.repositories;

import com.brazil.erudio.models.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository repository;

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Given Person Object When Save then Return Saved Person")
    @Test
    void testGivenPersonObject_WhenSave_thenReturnSavedPerson() {
        // Given / Arrange
        Person person = new Person("Colin", "Leech", "Mayfair - London - UK",
                "Male", "colin@erudio.com.br");

        // When / Act
        Person savedPerson = repository.save(person);

        // Then / Assert
        assertNotNull(savedPerson);
        assertTrue(savedPerson.getId() > 0);
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Given Person List When FindAll Then Return Person List")
    @Test
    void testGivenPersonList_WhenFindAll_ThenReturnPersonList() {
        // Given / Arrange
        Person personZero = new Person("Noah", "Butterworth", "Nuneaton - Warwickshire - UK",
                "Male", "noah@erudio.com.br");

        Person personOne = new Person("Matthew", "Davenport", "Oxford - Oxfordshire - UK",
                "Male", "matthew@erudio.com.br");

        repository.save(personZero);
        repository.save(personOne);

        // When / Act
        List<Person> personList = repository.findAll();

        // Then / Assert
        assertNotNull(personList);
        assertEquals(2, personList.size());
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Given Person Object when FindByID then Return Person Object")
    @Test
    void testGivenPersonObject_whenFindByID_thenReturnPersonObject() {
        // Given / Arrange
        Person person = new Person("Luke", "Austerfield", "Bakewell - Derbyshire - UK",
                "Male", "luke@erudio.com.br");
        repository.save(person);

        // When / Act
        Person savedPerson = repository.findById(person.getId()).get();

        // Then / Assert
        assertNotNull(savedPerson);
        assertEquals(person.getId(), savedPerson.getId());
    }
}