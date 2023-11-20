package org.example.kafka;

import lombok.Data;
import org.example.DocumentDTO;
import org.example.Status;
import org.example.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @Autowired
    private DocumentService documentService;

    @KafkaListener(topics = "documents", groupId = "group-id1")
    public void consume(@Payload DocumentDTO message, Acknowledgment acknowledgment) {
        System.out.println("Сообщение:" + message);
        acknowledgment.acknowledge();
    }
    @KafkaListener(topics = "documents_answer", groupId = "group-id2")
    public void consumeAnswer(@Payload DocumentDTO message, Acknowledgment acknowledgment) {
        System.out.println("Ответ:" + message);
        String statusCodeRecord = documentService.get(message.getId()).getStatus().getCode();
        if (!("IN_PROCESS").equals(statusCodeRecord)){
            throw new IllegalArgumentException("Этот документ не находится в обработке!");
        }
        documentService.update(message.getId(), message.getStatus().getCode());
        acknowledgment.acknowledge();
    }

}
