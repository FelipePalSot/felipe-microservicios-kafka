package com.tecsup.app.micro.payment.infrastructure.kafka.producer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.app.micro.payment.infrastructure.kafka.event.PaymentEventMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
@Component @RequiredArgsConstructor @Slf4j
public class PaymentEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    @Value("${kafka.topic.payment-events}") private String paymentTopic;
    public void publish(PaymentEventMessage event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(paymentTopic, String.valueOf(event.getPaymentId()), json);
            log.info("[KAFKA PRODUCER - PAYMENT] Sent {} | paymentId={}, enrollmentId={}, amount={}",
                event.getEventType(), event.getPaymentId(), event.getEnrollmentId(), event.getAmount());
        } catch (Exception e) {
            log.error("[KAFKA PRODUCER - PAYMENT] Error: {}", e.getMessage());
        }
    }
}
