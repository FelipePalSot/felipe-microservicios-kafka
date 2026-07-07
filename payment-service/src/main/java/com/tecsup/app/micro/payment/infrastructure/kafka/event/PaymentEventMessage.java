package com.tecsup.app.micro.payment.infrastructure.kafka.event;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data @Builder @NoArgsConstructor @AllArgsConstructor @JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentEventMessage {
    private String eventType;   // PAYMENT_APPROVED | PAYMENT_REJECTED
    private Long paymentId;
    private Long enrollmentId;
    private BigDecimal amount;
    private String reason;
    @Builder.Default private LocalDateTime timestamp = LocalDateTime.now();
}
