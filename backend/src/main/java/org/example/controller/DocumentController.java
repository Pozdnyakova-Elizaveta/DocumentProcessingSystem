package org.example.controller;

import org.apache.kafka.common.KafkaException;
import org.example.DocumentDTO;
import org.example.annotation.LogMethodInfo;
import org.example.kafka.KafkaSender;
import org.example.service.DocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер приложения
 */
@RestController
@RequestMapping("/documents")
public class DocumentController {
    /**
     * Объект-Producer
     */
    private final KafkaSender kafkaSender;
    /**
     * Объект для выполнения сервисов
     */
    private final DocumentServiceImpl service;

    @Autowired
    DocumentController(KafkaSender kafkaSender, DocumentServiceImpl service) {
        this.kafkaSender = kafkaSender;
        this.service = service;
    }

    /**
     * Сохранение записи
     *
     * @param dto полученный DTO-документ
     * @return сохраненный документ
     */
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @LogMethodInfo
    public DocumentDTO save(@Valid @RequestBody DocumentDTO dto) {
        return service.save(dto);
    }

    /**
     * Получение всех записей документов
     *
     * @return лист с документами
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @LogMethodInfo
    public List<DocumentDTO> get() {
        return service.findAll();
    }

    /**
     * Удаление документа по id
     *
     * @param id объект с id выбранного документа
     */
    @DeleteMapping(path = "/{id}")
    @LogMethodInfo
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

    /**
     * Отправка документа в обработку
     *
     * @param id выбранный документ
     * @return выбранный документ со статусом "В обработке"
     * или тот же документ, если не удалось отправить сообщение в кафку
     */
    @PostMapping(
            path = "send",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @LogMethodInfo
    public DocumentDTO send(@RequestBody IdDTO id) {
        DocumentDTO documentDTO = service.get(id.getId());
        try {
            kafkaSender.sendMessage(service.get(id.getId()));
            documentDTO =service.update(id.getId(), "IN_PROCESS");
        } catch (KafkaException e){
            System.out.println("Не удалось отправить сообщение в Kafka");
        }
        return documentDTO;
    }

    /**
     * Удаление нескольких документов
     *
     * @param idsDto объект с листом выбранных id документов
     */
    @DeleteMapping
    @LogMethodInfo
    public void deleteAll(@RequestBody IdsDTO idsDto) {
        service.deleteAll(idsDto.getIds());
    }
}