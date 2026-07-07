package com.tecsup.app.micro.enrollment.application.usecase;
import com.tecsup.app.micro.enrollment.domain.model.*;
import com.tecsup.app.micro.enrollment.domain.repository.EnrollmentRepository;
import com.tecsup.app.micro.enrollment.infrastructure.client.CourseClient;
import com.tecsup.app.micro.enrollment.infrastructure.client.UserClient;
import com.tecsup.app.micro.enrollment.infrastructure.kafka.event.EnrollmentEventMessage;
import com.tecsup.app.micro.enrollment.infrastructure.kafka.producer.EnrollmentEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Component @RequiredArgsConstructor @Slf4j
public class CreateEnrollmentUseCase {
    private final EnrollmentRepository enrollmentRepository;
    private final UserClient userClient;
    private final CourseClient courseClient;
    private final EnrollmentEventProducer enrollmentEventProducer;

    public Enrollment execute(Long userId, Long courseId) {
        log.info("[ENROLLMENT-SERVICE] === CREATING ENROLLMENT: userId={}, courseId={} ===", userId, courseId);
        // Paso 1: Validar usuario via RestTemplate (sincrono)
        var user = userClient.getUserById(userId);
        log.info("[ENROLLMENT-SERVICE] Step 1 OK - User validated: {}", user.getName());
        // Paso 2: Validar curso via RestTemplate (sincrono)
        var course = courseClient.getCourseById(courseId);
        log.info("[ENROLLMENT-SERVICE] Step 2 OK - Course validated: {}", course.getTitle());
        // Paso 3: Crear matricula PENDING_PAYMENT
        Enrollment enrollment = Enrollment.builder()
            .userId(userId).courseId(courseId).status(EnrollmentStatus.PENDING_PAYMENT).build();
        Enrollment saved = enrollmentRepository.save(enrollment);
        log.info("[ENROLLMENT-SERVICE] Step 3 OK - Enrollment CREATED: id={}, status={}", saved.getId(), saved.getStatus());
        // Paso 4: Publicar EnrollmentCreatedEvent a Kafka (asincrono)
        enrollmentEventProducer.publish(EnrollmentEventMessage.builder()
            .eventType("ENROLLMENT_CREATED")
            .enrollmentId(saved.getId()).userId(saved.getUserId())
            .courseId(saved.getCourseId()).status(saved.getStatus().name()).build());
        log.info("[ENROLLMENT-SERVICE] Step 4 OK - EnrollmentCreatedEvent published to Kafka");
        return saved;
    }
}
