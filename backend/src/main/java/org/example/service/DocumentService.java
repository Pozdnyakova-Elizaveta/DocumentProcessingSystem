package org.example.service;

import org.example.DTO.DocumentDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Интерфейс сервиса
 */
@Service
public interface DocumentService {
    /**
     * Сохранить документ
     *
     * @param documentDto документ
     * @return сохраненный документ
     */
    DocumentDTO save(DocumentDTO documentDto);

    /**
     * Удалить документ
     *
     * @param ids идентификаторы удаляемых документов
     */
    void deleteAll(Set<Long> ids);

    /**
     * Удалить документ по id
     *
     * @param id идентификатор документа
     */
    void delete(Long id);

    /**
     * Обновить статус документа
     *
     * @param id         идентификатор обновляемого документа
     * @param codeStatus новое значение кода статуса
     * @return обновленный документ
     */
    DocumentDTO update(Long id, String codeStatus);

    /**
     * Получить все документы
     *
     * @return список документов
     */
    List<DocumentDTO> findAll();

    /**
     * Получить документ по номеру
     *
     * @param id идентификатор
     * @return документ
     */
    DocumentDTO get(Long id);
}