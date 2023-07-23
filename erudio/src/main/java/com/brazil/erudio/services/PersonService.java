package com.brazil.erudio.services;

import com.brazil.erudio.models.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = getLogger(PersonService.class.getName());

    public Person findById(String id) {
        logger.info("Finding one person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Edward");
        person.setLastName("Hammond");
        person.setAddress("Boston - Illinois - USA");
        person.setGender("Male");

        return person;
    }

}
