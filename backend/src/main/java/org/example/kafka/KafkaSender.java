package org.example.kafka;

import org.example.DTO.DocumentDTO;
import org.example.service.DocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Класс-Producer
 */
@Component
public class KafkaSender {
    /**
     * Обертка для отправки сообщений
     */
    private final KafkaTemplate<Long, DocumentDTO> kafkaTemplate;
    /**
     * Объект для выполнения сервисов
     */
    private final DocumentServiceImpl documentService;

    @Autowired
    public KafkaSender(KafkaTemplate<Long, DocumentDTO> kafkaTemplate, DocumentServiceImpl documentService) {
        this.kafkaTemplate = kafkaTemplate;
        this.documentService=documentService;
    }

    /**
     * Метод для отправки сообщения
     * @param key ключ сообщения
     * @param message сообщение
     */
    public void sendMessage(Long key, DocumentDTO message) {
            ListenableFuture<SendResult<Long, DocumentDTO>> future =
                    kafkaTemplate.send("documents", key, message);
            future.addCallback(new ListenableFutureCallback<>() {

                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("failure: " + ex.getMessage());
                }

                @Override
                public void onSuccess(SendResult<Long, DocumentDTO> result) {
                    documentService.update(message.getId(), "IN_PROCESS");
                    System.out.println("success");
                }
            });
    }
}
