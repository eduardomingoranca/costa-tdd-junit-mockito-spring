package com.brazil.erudio;

import com.brazil.erudio.service.IPersonService;
import com.brazil.erudio.service.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PersonServiceTest {

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("When Create a Person with Success Should Return a Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldReturnPersonObject() {
        // Given / Arrange
        IPersonService service = new PersonService();
        // When / Act

        // Then / Assert

    }

}
