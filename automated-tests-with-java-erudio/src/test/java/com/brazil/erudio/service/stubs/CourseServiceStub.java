package com.brazil.erudio.service.stubs;

import com.brazil.erudio.service.CourseService;

import java.util.List;

import static java.util.Arrays.asList;

public class CourseServiceStub implements CourseService {
    @Override
    public List<String> retrieveCourses(String student) {
        return asList(
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

    @Override
    public List<String> doSomething(String student) {
        return null;
    }

    @Override
    public void deleteCourse(String course) {

    }

}
