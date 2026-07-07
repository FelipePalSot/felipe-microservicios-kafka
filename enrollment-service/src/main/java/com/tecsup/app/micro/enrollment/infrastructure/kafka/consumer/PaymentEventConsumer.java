package com.tecsup.app.micro.enrollment.infrastructure.kafka.consumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.app.micro.enrollment.domain.model.Enrollment;
import com.tecsup.app.micro.enrollment.domain.model.EnrollmentStatus;
import com.tecsup.app.micro.enrollment.domain.repository.EnrollmentRepository;
import com.tecsup.app.micro.enrollment.infrastructure.kafka.event.EnrollmentEventMessage;
import com.tecsup.app.micro.enrollment.infrastructure.kafka.event.PaymentEventMessage;
import com.tecsup.app.micro.enrollment.infrastructure.kafka.producer.EnrollmentEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Component @RequiredArgsConstructor @Slf4j
public class PaymentEventConsumer {
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentEventProducer enrollmentEventProducer;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${kafka.topic.payment-events}", groupId = "${spring.kafka.consumer.group-id}")
    public void handlePaymentEvent(String message) {
        log.info("[KAFKA CONSUMER - ENROLLMENT] Received payment event: {}", message);
        try {
            PaymentEventMessage event = objectMapper.readValue(message, PaymentEventMessage.class);
            Long enrollmentId = event.getEnrollmentId();
            Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found: " + enrollmentId));

            EnrollmentStatus newStatus;
            if ("PAYMENT_APPROVED".equals(event.getEventType())) {
                newStatus = EnrollmentStatus.CONFIRMED;
                log.info("[ENROLLMENT-SERVICE] PAYMENT_APPROVED -> enrollmentId={} -> CONFIRMED", enrollmentId);
            } else {
                newStatus = EnrollmentStatus.CANCELLED;
                log.warn("[ENROLLMENT-SERVICE] PAYMENT_REJECTED (reason={}) -> enrollmentId={} -> CANCELLED",
                    event.getReason(), enrollmentId);
            }
            enrollment.setStatus(newStatus);
            Enrollment updated = enrollmentRepository.save(enrollment);
            log.info("[ENROLLMENT-SERVICE] Enrollment status updated: id={}, newStatus={}", updated.getId(), updated.getStatus());

            // Publicar EnrollmentUpdatedEvent
            EnrollmentEventMessage updatedEvent = EnrollmentEventMessage.builder()
                .eventType("ENROLLMENT_UPDATED")
                .enrollmentId(updated.getId())
                .userId(updated.getUserId())
                .courseId(updated.getCourseId())
                .status(updated.getStatus().name())
                .build();
            enrollmentEventProducer.publish(updatedEvent);
        } catch (Exception e) {
            log.error("[KAFKA CONSUMER - ENROLLMENT] Error processing: {}", e.getMessage());
        }
    }
}
