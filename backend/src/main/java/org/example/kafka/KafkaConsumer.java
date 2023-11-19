package org.example.kafka;

import lombok.Data;
import org.example.DocumentDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "documents", groupId = "group_id")
    public void consume(@Payload DocumentDTO message, Acknowledgment acknowledgment) {
        System.out.println("Сообщение:" + message);
        acknowledgment.acknowledge();
    }
}
