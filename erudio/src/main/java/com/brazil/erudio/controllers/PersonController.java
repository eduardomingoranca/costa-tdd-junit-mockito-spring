package com.brazil.erudio.controllers;

import com.brazil.erudio.models.Person;
import com.brazil.erudio.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> findById(@PathVariable(value = "id") Long id) {
        try {
            return ok(service.findById(id));
        } catch (Exception e) {
            return notFound().build();
        }
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Person> findAll() {
        return service.findAll();
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return status(CREATED).body(service.create(person));
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> update(@RequestBody Person person) {
        try {
            return ok(service.update(person));
        } catch (Exception e) {
            return notFound().build();
        }
    }
}
