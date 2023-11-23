package org.example.kafka.config;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.DocumentDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс настройки Consumer
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    /**
     * Создание объекта ConsumerFactory со стратегией создания Consumer
     *
     * @return созданный ConsumerFactory
     */
    public ConsumerFactory<String, DocumentDTO> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:29092");
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "org.example");
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, DocumentDTO.class);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group-id");
        DefaultKafkaConsumerFactory<String, DocumentDTO> factory = new DefaultKafkaConsumerFactory<>(config);
        return factory;
    }

    /**
     * Создание фабрики для приема сообщений
     *
     * @return созданная фабрика
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DocumentDTO> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, DocumentDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        ContainerProperties containerProperties = factory.getContainerProperties();
        containerProperties.setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        containerProperties.setSyncCommits(true);
        return factory;
    }

}
