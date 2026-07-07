package com.tecsup.app.micro.enrollment.infrastructure.web.dto;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class EnrollmentResponse {
    private Long id;
    private Long userId;
    private Long courseId;
    private String status;
    private LocalDateTime createdAt;
}
