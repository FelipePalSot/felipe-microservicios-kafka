package com.tecsup.app.micro.payment.domain.model;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Payment {
    private Long id;
    private Long enrollmentId;
    private BigDecimal amount;
    private PaymentStatus status;
    private String rejectionReason;
    private LocalDateTime paidAt;
}
