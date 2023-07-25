package com.brazil.erudio.repositories;

import com.brazil.erudio.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);

    // Define custom query using JPQL with index parameters
    @Query("select p from Person p where p.firstName =?1 and p.lastName =?2")
    Person findJPQL(String firstName, String lastName);
}
