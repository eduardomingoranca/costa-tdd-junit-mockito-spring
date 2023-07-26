package com.brazil.erudio.services;

import com.brazil.erudio.exceptions.ResourceNotFoundException;
import com.brazil.erudio.models.Person;
import com.brazil.erudio.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    private Person person;

    @BeforeEach
    void setup() {
        // Given / Arrange
        person = new Person("Noah", "Worth", "noah@erudio.com.br",
                "Nuneaton - Warwickshire - UK", "Male");
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Given Person Object When Save Person then Return Person Object")
    @Test
    void testGivenPersonObject_WhenSavePerson_thenReturnPersonObject() {
        // Given / Arrange
        given(repository.findByEmail(anyString())).willReturn(empty());
        given(repository.save(person)).willReturn(person);

        // When / Act
        Person savedPerson = service.create(person);

        // Then / Assert
        assertNotNull(savedPerson);
        assertEquals("Noah", savedPerson.getFirstName());
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Given Existing Email When Save Person then Throws Exception")
    @Test
    void testGivenExistingEmail_WhenSavePerson_thenThrowsException() {
        // Given / Arrange
        given(repository.findByEmail(anyString())).willReturn(of(person));

        // When / Act
        assertThrows(ResourceNotFoundException.class, () -> service.create(person));

        // Then / Assert
        verify(repository, never()).save(any(Person.class));
    }
}