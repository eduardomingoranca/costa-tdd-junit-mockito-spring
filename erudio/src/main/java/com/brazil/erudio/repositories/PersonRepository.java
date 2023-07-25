package com.brazil.erudio.repositories;

import com.brazil.erudio.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);

    // Define custom query using JPQL with index parameters
    @Query("select p from Person p where p.firstName =?1 and p.lastName =?2")
    Person findJPQL(String firstName, String lastName);

    // Define custom query using JPQL with named parameters
    @Query("select p from Person p where p.firstName =:firstName and p.lastName =:lastName")
    Person findJQPLNameParameters(@Param("firstName") String firstName,
                                  @Param("lastName") String lastName);

    // Define custom query using Native SQL with index parameters
    @Query(value = "select * from person p where p.first_name =?1 and p.last_name =?2", nativeQuery = true)
    Person findNativeSQL(String firstName, String lastName);
}
