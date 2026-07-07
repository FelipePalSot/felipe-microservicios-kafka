package com.tecsup.app.micro.payment.infrastructure.web.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class CreatePaymentRequest {
    @NotNull(message = "enrollmentId is required") private Long enrollmentId;
    @NotNull @Positive(message = "amount must be positive") private BigDecimal amount;
    // APPROVED or REJECTED (default APPROVED if null)
    private String status;
    private String rejectionReason;
}
