package com.tecsup.app.micro.course.infrastructure.config;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
@Configuration
public class KafkaTopicConfig {
    @Value("${kafka.topic.course-events}")
    private String courseEventsTopic;
    @Bean
    public NewTopic courseEventsTopic() {
        return TopicBuilder.name(courseEventsTopic).partitions(1).replicas(1).build();
    }
}
