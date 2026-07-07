package com.tecsup.app.micro.payment.infrastructure.web.dto;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class PaymentResponse {
    private Long id;
    private Long enrollmentId;
    private BigDecimal amount;
    private String status;
    private String rejectionReason;
    private LocalDateTime paidAt;
}
