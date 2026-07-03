package pe.edu.tecsup.lms.courses.domain.repository;


import pe.edu.tecsup.lms.courses.domain.model.Course;

import java.util.Optional;

public interface CourseRepository {

    Course save(Course course) ;

    Optional<Course> findById(Long courseId);
}