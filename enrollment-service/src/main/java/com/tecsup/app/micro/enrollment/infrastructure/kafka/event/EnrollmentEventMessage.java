package com.tecsup.app.micro.enrollment.infrastructure.kafka.event;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.time.LocalDateTime;
@Data @Builder @NoArgsConstructor @AllArgsConstructor @JsonIgnoreProperties(ignoreUnknown = true)
public class EnrollmentEventMessage {
    private String eventType;
    private Long enrollmentId;
    private Long userId;
    private Long courseId;
    private String status;
    @Builder.Default private LocalDateTime timestamp = LocalDateTime.now();
}
