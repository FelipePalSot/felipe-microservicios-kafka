package com.tecsup.app.micro.payment.infrastructure.persistence.entity;
import com.tecsup.app.micro.payment.domain.model.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity @Table(name = "payments")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PaymentEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "enrollment_id", nullable = false) private Long enrollmentId;
    @Column(nullable = false, precision = 10, scale = 2) private BigDecimal amount;
    @Enumerated(EnumType.STRING) @Column(length = 30) private PaymentStatus status;
    @Column(name = "rejection_reason") private String rejectionReason;
    @Column(name = "paid_at", nullable = false, updatable = false) private LocalDateTime paidAt;
    @PrePersist protected void onCreate() { paidAt = LocalDateTime.now(); }
}
