package com.tecsup.app.micro.notification.infrastructure.web.dto;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class NotificationResponse {
    private Long id;
    private String eventType;
    private Long referenceId;
    private String message;
    private Boolean sent;
    private LocalDateTime createdAt;
}
