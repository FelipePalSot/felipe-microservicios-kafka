package pe.edu.tecsup.lms.courses.application;


import pe.edu.tecsup.lms.courses.domain.model.Course;

public interface CreateCourseUseCase {

    Course createCourse(String title, String description, String instructor);

}
