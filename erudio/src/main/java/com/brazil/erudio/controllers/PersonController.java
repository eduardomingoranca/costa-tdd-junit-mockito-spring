package com.brazil.erudio.controllers;

import com.brazil.erudio.models.Person;
import com.brazil.erudio.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable(value = "id") String id) {
        return service.findById(id);
    }
}
