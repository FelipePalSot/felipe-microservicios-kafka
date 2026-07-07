package com.tecsup.app.micro.course.infrastructure.kafka.producer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.app.micro.course.infrastructure.kafka.event.CourseEventMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
@Component @RequiredArgsConstructor @Slf4j
public class CourseEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    @Value("${kafka.topic.course-events}")
    private String courseTopic;
    public void publish(CourseEventMessage event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(courseTopic, String.valueOf(event.getCourseId()), json);
            log.info("[KAFKA PRODUCER] Sent {} to topic={} | courseId={}", 
                event.getEventType(), courseTopic, event.getCourseId());
        } catch (Exception e) {
            log.error("[KAFKA PRODUCER] Error publishing event: {}", e.getMessage());
        }
    }
}
