package org.example.kafka;

import org.example.DocumentDTO;
import org.example.service.DocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Класс-Consumer
 */
@Component
public class KafkaConsumer {
    /**
     * Объект для выполнения сервисов
     */
    private final DocumentServiceImpl documentService;

    @Autowired
    public KafkaConsumer(DocumentServiceImpl documentService) {
        this.documentService = documentService;
    }

    /**
     * Метод обработки сообщения из топика documents
     *
     * @param message        полученное сообщение
     * @param acknowledgment объект для выполнения ручного коммита
     */
    @KafkaListener(topics = "documents", groupId = "group-id")
    public void consume(@Payload DocumentDTO message, Acknowledgment acknowledgment) {
        System.out.println("Сообщение:" + message);
        acknowledgment.acknowledge();
    }

    /**
     * Метод обработки сообщения из топика documents_answer
     *
     * @param message        полученное сообщение
     * @param acknowledgment объект для выполнения ручного коммита
     */
    @KafkaListener(topics = "documents_answer", groupId = "group-id2")
    public void consumeAnswer(@Payload DocumentDTO message, Acknowledgment acknowledgment) {
        System.out.println("Ответ:" + message);
        String statusCodeRecord = documentService.get(message.getId()).getStatus().getCode();
        if (!("IN_PROCESS").equals(statusCodeRecord)) {
            throw new IllegalArgumentException("Этот документ не находится в обработке!");
        }
        documentService.update(message.getId(), message.getStatus().getCode());
        acknowledgment.acknowledge();
    }

}
