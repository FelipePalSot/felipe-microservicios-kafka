package com.tecsup.app.micro.enrollment.infrastructure.kafka.producer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.app.micro.enrollment.infrastructure.kafka.event.EnrollmentEventMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
@Component @RequiredArgsConstructor @Slf4j
public class EnrollmentEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    @Value("${kafka.topic.enrollment-events}") private String enrollmentTopic;
    public void publish(EnrollmentEventMessage event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(enrollmentTopic, String.valueOf(event.getEnrollmentId()), json);
            log.info("[KAFKA PRODUCER - ENROLLMENT] Sent {} | enrollmentId={}, userId={}, status={}",
                event.getEventType(), event.getEnrollmentId(), event.getUserId(), event.getStatus());
        } catch (Exception e) {
            log.error("[KAFKA PRODUCER - ENROLLMENT] Error: {}", e.getMessage());
        }
    }
}
