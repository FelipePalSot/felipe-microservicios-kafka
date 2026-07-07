package com.tecsup.app.micro.course.application.usecase;
import com.tecsup.app.micro.course.domain.exception.CourseNotFoundException;
import com.tecsup.app.micro.course.domain.model.Course;
import com.tecsup.app.micro.course.domain.repository.CourseRepository;
import com.tecsup.app.micro.course.infrastructure.kafka.event.CourseEventMessage;
import com.tecsup.app.micro.course.infrastructure.kafka.producer.CourseEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Component @RequiredArgsConstructor @Slf4j
public class PublishCourseUseCase {
    private final CourseRepository courseRepository;
    private final CourseEventProducer courseEventProducer;
    public Course execute(Long id) {
        log.debug("Executing PublishCourseUseCase for id={}", id);
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new CourseNotFoundException(id));
        course.setPublished(true);
        Course updated = courseRepository.save(course);
        log.info("[COURSE-SERVICE] Course PUBLISHED: id={}, title={}", updated.getId(), updated.getTitle());
        // Publicar evento CoursePublishedEvent
        CourseEventMessage event = CourseEventMessage.builder()
            .eventType("COURSE_PUBLISHED")
            .courseId(updated.getId())
            .title(updated.getTitle())
            .description(updated.getDescription())
            .build();
        courseEventProducer.publish(event);
        return updated;
    }
}
