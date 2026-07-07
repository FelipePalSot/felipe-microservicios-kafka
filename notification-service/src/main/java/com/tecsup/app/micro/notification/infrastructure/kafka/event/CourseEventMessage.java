package com.tecsup.app.micro.notification.infrastructure.kafka.event;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.time.LocalDateTime;
@Data @Builder @NoArgsConstructor @AllArgsConstructor @JsonIgnoreProperties(ignoreUnknown = true)
public class CourseEventMessage {
    private String eventType;   // COURSE_CREATED | COURSE_PUBLISHED
    private Long courseId;
    private String title;
    private String description;
    private LocalDateTime timestamp;
}
