package com.tecsup.app.micro.notification.domain.model;
import lombok.*;
import java.time.LocalDateTime;
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Notification {
    private Long id;
    private String eventType;
    private Long referenceId;    // courseId
    private String message;
    private Boolean sent;
    private LocalDateTime createdAt;
}
