package com.brazil.erudio.business;

import com.brazil.erudio.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

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

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Delete Courses not Related to Spring Using Mockito should call Method")
    @Test
    void testDeleteCoursesNotRelatedToSpring_UsingMockitoVerify_Should_CallMethod_deleteCourse() {
        // Given / Arrange
        given(mockService.retrieveCourses("Edward")).willReturn(courses);

        // When / Act
        business.deleteCoursesNotRelatedToSpring("Edward");

        // Then / Assert
        verify(mockService, times(1)).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
        // poderia ser atLeast(1)
        verify(mockService, atLeastOnce()).deleteCourse("Arquitetura de Microsservicos do 0 com ASP.NET, .NET 6 e C#");
        verify(mockService, never()).deleteCourse("REST API's RESTFul do 0 a AWS com Spring Boot 3 Java e Docker");
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Delete Courses not Related to Spring Using Mockito should call Method Version Two")
    @Test
    void testDeleteCoursesNotRelatedToSpring_UsingMockitoVerify_Should_CallMethod_deleteCourseVersionTwo() {
        // Given / Arrange
        given(mockService.retrieveCourses("Edward")).willReturn(courses);

        // When / Act
        business.deleteCoursesNotRelatedToSpring("Edward");

        // Then / Assert
        then(mockService).should().deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
        then(mockService).should().deleteCourse("Arquitetura de Microsservicos do 0 com ASP.NET, .NET 6 e C#");
        then(mockService).should(never())
                .deleteCourse("REST API's RESTFul do 0 a AWS com Spring Boot 3 Java e Docker");
    }

}