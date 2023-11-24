package org.example;

import org.example.DTO.DocumentDTO;
import org.example.DTO.Status;
import org.example.service.DocumentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тестирование сервисов
 */
@SpringBootTest
@ActiveProfiles("test")
public class DocumentServiceImplTest {
    /**
     * Объект для выполнения сервисов
     */
    private final DocumentServiceImpl documentService;

    @Autowired
    public DocumentServiceImplTest(DocumentServiceImpl documentService) {
        this.documentService = documentService;
    }

    /**
     * Документы для тестирования
     */
    private final DocumentDTO firstExampleDocumentDTO = DocumentDTO.builder().patient("Иванов И. И.")
            .description("Больничный лист").organization("Поликлиника №2").type("Больничный")
            .date(new Date()).status(new Status("NEW")).build();
    private final DocumentDTO secondExampleDocumentDTO = DocumentDTO.builder().patient("Петров В. С.")
            .description("Талон").organization("Поликлиника №10").type("Талон к терапевту")
            .date(new Date()).status(new Status("NEW")).build();

    @BeforeEach
    public void before() {
        List<DocumentDTO> documentDTOs = documentService.findAll();
        for (DocumentDTO documentDTO : documentDTOs) {
            documentService.delete(documentDTO.getId());
        }
    }

    /**
     * Тест сохранения и получения документа
     */
    @Test
    public void saveGetTest() {
        DocumentDTO saveDocumentDTO = documentService.save(firstExampleDocumentDTO);
        Long id = saveDocumentDTO.getId();
        DocumentDTO getDocumentDTO = documentService.get(id);
        assertEquals(1, documentService.findAll().size());
        assertEquals(id, getDocumentDTO.getId());
    }

    /**
     * Тест обновления статуса
     */
    @Test
    void update() {
        DocumentDTO saveDocumentDTO = documentService.save(firstExampleDocumentDTO);
        documentService.update(saveDocumentDTO.getId(), "IN_PROCESS");
        DocumentDTO getDocumentDTO = documentService.get(saveDocumentDTO.getId());
        assertEquals("В обработке", getDocumentDTO.getStatus().getName());
        assertEquals("IN_PROCESS", getDocumentDTO.getStatus().getCode());
    }

    /**
     * Тест удаления одной записи
     */
    @Test
    void delete() {
        DocumentDTO saveDocumentDTO = documentService.save(firstExampleDocumentDTO);
        documentService.delete(saveDocumentDTO.getId());
        assertEquals(0, documentService.findAll().size());
    }

    /**
     * Тест удаления нескольких записей
     */
    @Test
    void deleteAll() {
        DocumentDTO saveDocumentDTO = documentService.save(firstExampleDocumentDTO);
        DocumentDTO secondSaveDocumentDTO = documentService.save(secondExampleDocumentDTO);
        documentService.deleteAll(Set.of(saveDocumentDTO.getId(), secondSaveDocumentDTO.getId()));
        assertEquals(0, documentService.findAll().size());
    }

    /**
     * Тест получения всех документов
     */
    @Test
    void findAll() {
        DocumentDTO saveDocumentDTO = documentService.save(firstExampleDocumentDTO);
        DocumentDTO secondSaveDocumentDTO = documentService.save(secondExampleDocumentDTO);
        Map<Long, DocumentDTO> allDocumentMap = documentService.findAll()
                .stream()
                .collect(Collectors.toMap(DocumentDTO::getId, Function.identity()));
        assertEquals(2, allDocumentMap.size());
        assertNotNull(allDocumentMap.get(saveDocumentDTO.getId()));
        assertNotNull(allDocumentMap.get(secondSaveDocumentDTO.getId()));
    }

    /**
     * Тест неудачной попытки получить несуществующий документ
     */
    @Test
    void getWhenNotExistsTest() {
        Assertions.assertThrows(IllegalStateException.class, () -> documentService.get(5L));
    }

    /**
     * Тест неудачной попытки сохранить пустую запись
     */
    @Test
    void saveEmptyTest() {
        DocumentDTO documentDTO = new DocumentDTO();
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> documentService.save(documentDTO));
    }
}
