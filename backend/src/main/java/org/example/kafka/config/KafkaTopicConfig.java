package org.example.kafka.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для создания топиков
 */
@EnableKafka
@Configuration
public class KafkaTopicConfig {
    /**
     * Адрес брокера кафки
     */
    private final String bootstrapAddress = "localhost:29092";

    /**
     * Создание KafkaAdmin для работы с топиками
     *
     * @return созданный KafkaAdmin
     */
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    /**
     * Создание топика для получения сообщений с отправленными документами
     *
     * @return созданный топик
     */
    @Bean
    public NewTopic topicDocuments() {
        return TopicBuilder.name("documents").partitions(1).replicas(1).build();
    }

    /**
     * Создание топика для ручного добавления ответа на присланный документ
     *
     * @return созданный топик
     */
    @Bean
    public NewTopic topicDocumentsAnswer() {
        return TopicBuilder.name("documents_answer").partitions(1).replicas(1).build();
    }
}