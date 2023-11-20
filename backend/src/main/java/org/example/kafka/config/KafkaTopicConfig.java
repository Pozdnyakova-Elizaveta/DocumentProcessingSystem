package org.example.kafka.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;
@EnableKafka
@Configuration
public class KafkaTopicConfig {
    private String bootstrapAddress="localhost:29092";

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topicDocuments() {
        return TopicBuilder.name("documents").partitions(1).replicas(1).build();
    }
    @Bean
    public NewTopic topicDocumentsAnswer() {
        return TopicBuilder.name("documents_answer").partitions(1).replicas(1).build();
    }
}