package org.example.service;

import org.example.DTO.DocumentDTO;
import org.example.entity.DocumentEntity;
import org.example.DTO.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Имплементация собственного фасада
 */
public class CustomMapperFacadeImpl implements CustomMapperFacade {
    @Override
    public DocumentEntity mapToEntity(DocumentDTO documentDTO) {
        return DocumentEntity.builder().type(documentDTO.getType())
                .patient(documentDTO.getPatient())
                .description(documentDTO.getDescription())
                .date(documentDTO.getDate())
                .organization(documentDTO.getOrganization())
                .status(documentDTO.getStatus().getName()).build();
    }

    @Override
    public DocumentDTO mapToDTO(DocumentEntity documentEntity) {
        return DocumentDTO.builder().id(documentEntity.getId()).type(documentEntity.getType())
                .patient(documentEntity.getPatient())
                .description(documentEntity.getDescription())
                .date(documentEntity.getDate())
                .organization(documentEntity.getOrganization())
                .status(Status.ofName(documentEntity.getStatus())).build();
    }

    @Override
    public List<DocumentDTO> mapAsListDTO(List<DocumentEntity> entities) {
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentEntity entity : entities) {
            documentDTOS.add(mapToDTO(entity));
        }
        return documentDTOS;
    }
}