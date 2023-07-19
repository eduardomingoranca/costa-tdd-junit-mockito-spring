package com.brazil.erudio.service;

import com.brazil.erudio.model.Person;

import java.util.concurrent.atomic.AtomicLong;

public class PersonService implements IPersonService {
    @Override
    public Person createPerson(Person person) {
        // isBlank -> vazio
        if (person.getEmail() == null || person.getEmail().isBlank())
            throw new IllegalArgumentException("The Person e-Mail is null or empty!");

        long id = new AtomicLong().incrementAndGet();
        person.setId(id);
        return person;
    }

}
