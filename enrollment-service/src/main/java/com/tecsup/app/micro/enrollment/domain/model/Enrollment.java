package com.tecsup.app.micro.enrollment.domain.model;
import lombok.*;
import java.time.LocalDateTime;
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Enrollment {
    private Long id;
    private Long userId;
    private Long courseId;
    private EnrollmentStatus status;
    private LocalDateTime createdAt;
}
