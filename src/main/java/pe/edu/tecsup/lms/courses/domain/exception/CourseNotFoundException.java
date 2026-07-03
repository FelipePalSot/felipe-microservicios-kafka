package pe.edu.tecsup.lms.courses.domain.exception;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(Long courseId) {
        super("Course not found: " + courseId);
    }
}
