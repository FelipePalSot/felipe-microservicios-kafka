package com.tecsup.app.micro.course.application.usecase;
import com.tecsup.app.micro.course.domain.exception.InvalidCourseDataException;
import com.tecsup.app.micro.course.domain.model.Course;
import com.tecsup.app.micro.course.domain.repository.CourseRepository;
import com.tecsup.app.micro.course.infrastructure.kafka.event.CourseEventMessage;
import com.tecsup.app.micro.course.infrastructure.kafka.producer.CourseEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Component @RequiredArgsConstructor @Slf4j
public class CreateCourseUseCase {
    private final CourseRepository courseRepository;
    private final CourseEventProducer courseEventProducer;
    public Course execute(Course course) {
        log.debug("Executing CreateCourseUseCase: {}", course.getTitle());
        if (course.getTitle() == null || course.getTitle().isBlank()) {
            throw new InvalidCourseDataException("Course title is required");
        }
        course.setPublished(false);
        Course saved = courseRepository.save(course);
        log.info("[COURSE-SERVICE] Course created with id={}, title={}", saved.getId(), saved.getTitle());
        // Publicar evento CourseCreatedEvent
        CourseEventMessage event = CourseEventMessage.builder()
            .eventType("COURSE_CREATED")
            .courseId(saved.getId())
            .title(saved.getTitle())
            .description(saved.getDescription())
            .build();
        courseEventProducer.publish(event);
        return saved;
    }
}
