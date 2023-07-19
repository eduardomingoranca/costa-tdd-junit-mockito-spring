package com.brazil.erudio.business;

import com.brazil.erudio.service.CourseService;

import java.util.ArrayList;
import java.util.List;

// CourseBusiness = SUT - System (Method) Under Test
public class CourseBusiness {

    // CourseService is a Dependency
    private final CourseService service;

    public CourseBusiness(CourseService service) {
        this.service = service;
    }

    public List<String> retrieveCoursesRelatedToSpring(String student) {
        ArrayList<String> filteredCourses = new ArrayList<>();

        if ("Foo Bar".equals(student))
            return filteredCourses;

        // buscando todos os cursos que o estudante esta inscrito
        List<String> allCourses = service.retrieveCourses(student);

        // filtrando o curso Spring
        for (String course: allCourses) {
            if (course.contains("Spring"))
                filteredCourses.add(course);
        }

        return filteredCourses;
    }

    public void deleteCoursesNotRelatedToSpring(String student) {
        // buscando todos os cursos que o estudante esta inscrito
        List<String> allCourses = service.retrieveCourses(student);

        for (String course: allCourses) {
            if (!course.contains("Spring"))
                service.deleteCourse(course);
        }
    }
}
