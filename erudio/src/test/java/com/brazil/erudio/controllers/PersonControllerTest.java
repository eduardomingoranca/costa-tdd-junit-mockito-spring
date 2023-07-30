package com.brazil.erudio.controllers;

import com.brazil.erudio.exceptions.ResourceNotFoundException;
import com.brazil.erudio.models.Person;
import com.brazil.erudio.services.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private PersonService service;

    private Person person;

    @BeforeEach
    void setup() {
        // Given / Arrange
        person = new Person("Moses", "Cohen",
                "Mayfair - London - UK", "Male",
                "moses@erudio.com");
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Given Person Object When Create Person then Return Saved Person")
    @Test
    void testGivenPersonObject_WhenCreatePerson_thenReturnSavedPerson() throws Exception {
        // Given / Arrange
        given(service.create(any(Person.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        // When / Act
        ResultActions response = mockMvc.perform(post("/person")
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(person)));

        // Then / Assert
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(person.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person.getLastName())))
                .andExpect(jsonPath("$.email", is(person.getEmail())));
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Given List Of People When FindAll People then Return People List")
    @Test
    void testGivenListOfPeople_WhenFindAllPeople_thenReturnPeopleList() throws Exception {
        // Given / Arrange
        Person personOne = new Person("Noah", "Worth", "noah@erudio.com.br",
                "Nuneaton - Warwickshire - UK", "Male");

        List<Person> people = new ArrayList<>();
        people.add(person);
        people.add(personOne);

        given(service.findAll()).willReturn(people);

        // When / Act
        ResultActions response = mockMvc.perform(get("/person"));

        // Then / Assert
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(people.size())));
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Given Person ID When FindByID then Return Person Object")
    @Test
    void testGivenPersonID_WhenFindByID_thenReturnPersonObject() throws Exception {
        // Given / Arrange
        long personID = 1L;
        given(service.findById(personID)).willReturn(person);

        // When / Act
        ResultActions response = mockMvc.perform(get("/person/{id}", personID));

        // Then / Assert
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(person.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person.getLastName())))
                .andExpect(jsonPath("$.email", is(person.getEmail())));
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Given Invalid PersonId When FindById then Return Not Found")
    @Test
    void testGivenInvalidPersonId_WhenFindById_thenReturnNotFound() throws Exception {
        // Given / Arrange
        long personId = 1L;
        given(service.findById(personId)).willThrow(ResourceNotFoundException.class);

        // When / Act
        ResultActions response = mockMvc.perform(get("/person/{id}", personId));

        // Then / Assert
        response.andExpect(status().isNotFound()).andDo(print());
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Given Updated Person When Update then Return Updated Person Object")
    @Test
    void testGivenUpdatedPerson_WhenUpdate_thenReturnUpdatedPersonObject() throws Exception {
        // Given / Arrange
        long personId = 1L;
        given(service.findById(personId)).willThrow(ResourceNotFoundException.class);
        given(service.update(any(Person.class))).willAnswer((invocation) -> invocation.getArgument(0));

        // When / Act
        Person updatedPerson = new Person("Noah", "Worth",
                "noah@erudio.com.br", "Nuneaton - Warwickshire - UK",
                "Male");

        ResultActions response = mockMvc.perform(put("/person")
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(updatedPerson)));

        // Then / Assert
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(updatedPerson.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatedPerson.getLastName())))
                .andExpect(jsonPath("$.email", is(updatedPerson.getEmail())));
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Given Unexistent Person when Update then Return Not Found")
    @Test
    void testGivenUnexistentPerson_WhenUpdate_thenReturnNotFound() throws Exception {
        // Given / Arrange
        long personId = 1L;
        given(service.findById(personId)).willThrow(ResourceNotFoundException.class);
        given(service.update(any(Person.class))).willAnswer((invocation) -> invocation.getArgument(1));

        // When / Act
        Person updatedPerson = new Person("Noah", "Worth",
                "noah@erudio.com.br", "Nuneaton - Warwickshire - UK",
                "Male");

        ResultActions response = mockMvc.perform(put("/person")
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(updatedPerson)));

        // Then / Assert
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

}