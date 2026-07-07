package com.tecsup.app.micro.enrollment.infrastructure.config;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
@Configuration
public class KafkaTopicConfig {
    @Value("${kafka.topic.enrollment-events}") private String enrollmentTopic;
    @Bean public NewTopic enrollmentEventsTopic() {
        return TopicBuilder.name(enrollmentTopic).partitions(1).replicas(1).build();
    }
}
