package org.example.service;

import org.example.DocumentDTO;
import org.example.DocumentEntity;

import javax.print.Doc;
import java.util.List;

public interface CustomMapperFacade {
    DocumentEntity mapToEntity(DocumentDTO documentDTO);
    DocumentDTO mapToDTO(DocumentEntity documentEntity);
    List<DocumentDTO> mapAsListDTO(List<DocumentEntity> entities);
}