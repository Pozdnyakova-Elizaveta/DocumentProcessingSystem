package org.example.service;


import org.example.DocumentDTO;
import org.example.controller.IdsDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public interface DocumentService {
    /**
     * Сохранить документ
     * @param documentDto документ
     * @return сохраненный документ
     */
    DocumentDTO save(DocumentDTO documentDto);

    /**
     * Удалить документ
     * @param ids идентификаторы документов
     */
    void deleteAll(Set <Long> ids);

    /**
     * Удалить документ по ид
     * @param id идентификатор документа
     */
    void delete(Long id);

    /**
     * Обновить документ
     * @param id документ
     * @return обновленный документ
     */
    DocumentDTO update(Long id, String codeStatus);

    /**
     * Получить все документы
     * @return список документов
     */
    List<DocumentDTO> findAll();

    /**
     * Получить документ по номеру
     * @param id идентификатор
     * @return документ
     */
    DocumentDTO get(Long id);
}