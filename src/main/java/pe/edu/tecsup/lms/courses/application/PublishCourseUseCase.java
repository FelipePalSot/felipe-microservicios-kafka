package pe.edu.tecsup.lms.courses.application;


import pe.edu.tecsup.lms.courses.domain.model.Course;

public interface PublishCourseUseCase {

    Course publishCourse(Long courseId);

}
