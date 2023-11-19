package org.example;

import org.example.DocumentDTO;
import org.example.Status;
import org.example.service.DocumentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class DocumentServiceImplTest {
    @Autowired
    private DocumentServiceImpl documentService;
    private final DocumentDTO firstExampleDocumentDTO = DocumentDTO.builder().patient("Иванов И. И.")
            .description("Больничный лист").organization("Поликлиника №2").type("Больничный")
            .date(new Date()).status(Status.ofCode("NEW")).build();
    private final DocumentDTO secondExampleDocumentDTO = DocumentDTO.builder().patient("Петров В. С.")
            .description("Талон").organization("Поликлиника №10").type("Талон к терапевту")
            .date(new Date()).status(Status.ofCode("NEW")).build();

    @BeforeEach
    public void before() {
        List <DocumentDTO> documentDTOs = documentService.findAll();
        for (DocumentDTO documentDTO: documentDTOs ){
            documentService.delete(documentDTO.getId());
        }
    }

    @Test
    public void saveGetTest() {
        DocumentDTO saveDocumentDTO = documentService.save(firstExampleDocumentDTO);
        Long id = saveDocumentDTO.getId();
        DocumentDTO getDocumentDTO = documentService.get(id);
        assertEquals(1, documentService.findAll().size());
        assertEquals(id, getDocumentDTO.getId());
    }

    @Test
    void update() {
        DocumentDTO saveDocumentDTO = documentService.save(firstExampleDocumentDTO);
        documentService.update(saveDocumentDTO.getId(), "IN_PROCESS");
        DocumentDTO getDocumentDTO = documentService.get(saveDocumentDTO.getId());
        assertEquals("В обработке", getDocumentDTO.getStatus().getName());
        assertEquals("IN_PROCESS", getDocumentDTO.getStatus().getCode());
    }

    @Test
    void delete() {
        DocumentDTO saveDocumentDTO = documentService.save(firstExampleDocumentDTO);
        documentService.delete(saveDocumentDTO.getId());
        assertEquals(0, documentService.findAll().size());
    }

    @Test
    void deleteAll() {
        DocumentDTO saveDocumentDTO = documentService.save(firstExampleDocumentDTO);
        DocumentDTO secondSaveDocumentDTO = documentService.save(secondExampleDocumentDTO);
        documentService.deleteAll(Set.of(saveDocumentDTO.getId(), secondSaveDocumentDTO.getId()));
        assertEquals(0, documentService.findAll().size());
    }

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

    @Test
    void getWhenNotExistsTest() {
        Assertions.assertThrows(IllegalStateException.class, () -> documentService.get(5L));
    }
}
