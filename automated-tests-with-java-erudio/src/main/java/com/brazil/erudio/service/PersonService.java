package com.brazil.erudio.service;

import com.brazil.erudio.model.Person;

import java.util.concurrent.atomic.AtomicLong;

public class PersonService implements IPersonService {
    @Override
    public Person createPerson(Person person) {
        long id = new AtomicLong().incrementAndGet();
        person.setId(id);
        return person;
    }

}
