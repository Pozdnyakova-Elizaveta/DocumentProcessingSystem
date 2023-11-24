package org.example.kafka;

import org.example.DTO.DocumentDTO;
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

    @Autowired
    public KafkaSender(KafkaTemplate<Long, DocumentDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Метод отправки сообщений
     *
     * @param message отправляемый документ
     */
    public void sendMessage(Long key, DocumentDTO message) {

        ListenableFuture<SendResult<Long, DocumentDTO>> future =
                kafkaTemplate.send("documents", key, message);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("failure");
            }

            @Override
            public void onSuccess(SendResult<Long, DocumentDTO> result) {
                System.out.println("success");
            }
        });
    }
}
