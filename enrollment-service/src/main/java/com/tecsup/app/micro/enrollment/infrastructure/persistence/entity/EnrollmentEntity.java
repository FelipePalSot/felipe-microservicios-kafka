package com.tecsup.app.micro.enrollment.infrastructure.persistence.entity;
import com.tecsup.app.micro.enrollment.domain.model.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity @Table(name = "enrollments")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class EnrollmentEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "user_id", nullable = false) private Long userId;
    @Column(name = "course_id", nullable = false) private Long courseId;
    @Enumerated(EnumType.STRING) @Column(length = 40) private EnrollmentStatus status;
    @Column(name = "created_at", nullable = false, updatable = false) private LocalDateTime createdAt;
    @PrePersist protected void onCreate() { createdAt = LocalDateTime.now(); }
}
