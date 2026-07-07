package com.tecsup.app.micro.payment.infrastructure.config;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
@Configuration
public class KafkaTopicConfig {
    @Value("${kafka.topic.payment-events}") private String paymentTopic;
    @Bean public NewTopic paymentEventsTopic() {
        return TopicBuilder.name(paymentTopic).partitions(1).replicas(1).build();
    }
}
