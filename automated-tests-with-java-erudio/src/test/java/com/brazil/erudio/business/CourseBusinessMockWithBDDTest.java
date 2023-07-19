package com.brazil.erudio.business;

import com.brazil.erudio.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CourseBusinessMockWithBDDTest {

    CourseService mockService;
    CourseBusiness business;
    List<String> courses;

    @BeforeEach
    void setup() {
        // Given / Arrange
        mockService = mock(CourseService.class);
        business = new CourseBusiness(mockService);
        courses = asList(
                "REST API's RESTFul do 0 a Azure com ASP.NET Core 5 e Docker",
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "Spotify Engineering Culture Desmistificado",
                "REST API's RESTFul do 0 a AWS com Spring Boot 3 Java e Docker",
                "Docker do Zero a Maestria - Conteinerizacao Desmistificada",
                "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI",
                "Microsservicos do 0 com Spring Cloud, Spring Boot e Docker",
                "Arquitetura de Microsservicos do 0 com ASP.NET, .NET 6 e C#",
                "REST API's RESTFul do 0 a AWS com Spring Boot 3 Kotlin e Docker",
                "Kotlin para DEV's Java: Aprenda a Linguagem Padrao do Android",
                "Microsservicos do 0 com Spring Cloud, Kotlin e Docker"
        );
    }

    @Test
    void testCoursesRelatedToSpring_When_UsingAMock() {
        // When / Act
        given(mockService.retrieveCourses("Edward"))
                .willReturn(courses);

        List<String> filteredCourses = business.retrieveCoursesRelatedToSpring("Edward");

        // Then / Assert
        assertThat(filteredCourses.size(), is(4));
    }

}