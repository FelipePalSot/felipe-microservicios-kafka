package com.tecsup.app.micro.notification.infrastructure.kafka.consumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.app.micro.notification.domain.model.Notification;
import com.tecsup.app.micro.notification.domain.repository.NotificationRepository;
import com.tecsup.app.micro.notification.infrastructure.kafka.event.CourseEventMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Component @RequiredArgsConstructor @Slf4j
public class CourseEventHandler {
    private final NotificationRepository notificationRepository;
    private final ObjectMapper objectMapper;

    /**
     * Escucha SOLO eventos de curso en lms.course.events
     * CourseCreatedEvent y CoursePublishedEvent
     */
    @KafkaListener(topics = "${kafka.topic.course-events}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleCourseEvent(String message) {
        log.info("[KAFKA CONSUMER - NOTIFICATION] Received course event: {}", message);
        try {
            CourseEventMessage event = objectMapper.readValue(message, CourseEventMessage.class);
            String notificationMessage;
            if ("COURSE_CREATED".equals(event.getEventType())) {
                notificationMessage = String.format(
                    "[NOTIFICATION] Nuevo curso creado: '%s' (id=%d)",
                    event.getTitle(), event.getCourseId());
                log.info("[NOTIFICATION-SERVICE] CourseCreatedEvent received: courseId={}, title='{}'",
                    event.getCourseId(), event.getTitle());
            } else if ("COURSE_PUBLISHED".equals(event.getEventType())) {
                notificationMessage = String.format(
                    "[NOTIFICATION] Curso publicado: '%s' (id=%d) - Ya disponible para matricularse!",
                    event.getTitle(), event.getCourseId());
                log.info("[NOTIFICATION-SERVICE] CoursePublishedEvent received: courseId={}, title='{}'",
                    event.getCourseId(), event.getTitle());
            } else {
                log.warn("[NOTIFICATION-SERVICE] Unknown course event type: {}", event.getEventType());
                return;
            }
            // Guardar notificacion en DB
            Notification notification = Notification.builder()
                .eventType(event.getEventType())
                .referenceId(event.getCourseId())
                .message(notificationMessage)
                .sent(true)
                .build();
            notificationRepository.save(notification);
            log.info("[NOTIFICATION-SERVICE] Notification SAVED: {}", notificationMessage);
        } catch (Exception e) {
            log.error("[KAFKA CONSUMER - NOTIFICATION] Error processing course event: {}", e.getMessage());
        }
    }
}
