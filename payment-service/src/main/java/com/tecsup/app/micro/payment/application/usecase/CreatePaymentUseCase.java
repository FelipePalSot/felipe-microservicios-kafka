package com.tecsup.app.micro.payment.application.usecase;
import com.tecsup.app.micro.payment.domain.model.Payment;
import com.tecsup.app.micro.payment.domain.model.PaymentStatus;
import com.tecsup.app.micro.payment.domain.repository.PaymentRepository;
import com.tecsup.app.micro.payment.infrastructure.kafka.event.PaymentEventMessage;
import com.tecsup.app.micro.payment.infrastructure.kafka.producer.PaymentEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
@Component @RequiredArgsConstructor @Slf4j
public class CreatePaymentUseCase {
    private final PaymentRepository paymentRepository;
    private final PaymentEventProducer paymentEventProducer;

    public Payment execute(Long enrollmentId, BigDecimal amount, PaymentStatus status, String reason) {
        log.info("[PAYMENT-SERVICE] === PROCESSING PAYMENT: enrollmentId={}, amount={}, status={} ===",
            enrollmentId, amount, status);
        Payment payment = Payment.builder()
            .enrollmentId(enrollmentId)
            .amount(amount)
            .status(status)
            .rejectionReason(reason)
            .build();
        Payment saved = paymentRepository.save(payment);
        log.info("[PAYMENT-SERVICE] Payment SAVED: id={}, status={}", saved.getId(), saved.getStatus());

        // Publicar evento a Kafka
        String eventType = (status == PaymentStatus.APPROVED) ? "PAYMENT_APPROVED" : "PAYMENT_REJECTED";
        PaymentEventMessage event = PaymentEventMessage.builder()
            .eventType(eventType)
            .paymentId(saved.getId())
            .enrollmentId(saved.getEnrollmentId())
            .amount(saved.getAmount())
            .reason(saved.getRejectionReason())
            .build();
        paymentEventProducer.publish(event);
        log.info("[PAYMENT-SERVICE] {} published to Kafka for enrollmentId={}", eventType, enrollmentId);
        return saved;
    }
}
