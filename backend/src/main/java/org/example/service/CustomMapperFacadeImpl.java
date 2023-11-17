package org.example.service;

import org.example.DocumentDTO;
import org.example.DocumentEntity;
import org.example.Status;

import java.util.ArrayList;
import java.util.List;

public class CustomMapperFacadeImpl implements CustomMapperFacade{
    @Override
    public DocumentEntity mapToEntity(DocumentDTO documentDTO) {
        DocumentEntity entity = DocumentEntity.builder().type(documentDTO.getType())
                .patient(documentDTO.getPatient())
                .description(documentDTO.getDescription())
                .date(documentDTO.getDate())
                .organization(documentDTO.getOrganization())
                .status(documentDTO.getStatus().getName()).build();
        return entity;
    }

    @Override
    public DocumentDTO mapToDTO(DocumentEntity documentEntity) {
        DocumentDTO DTO = DocumentDTO.builder().id(documentEntity.getId()).type(documentEntity.getType())
                .patient(documentEntity.getPatient())
                .description(documentEntity.getDescription())
                .date(documentEntity.getDate())
                .organization(documentEntity.getOrganization())
                .status(Status.ofName(documentEntity.getStatus())).build();
        return DTO;
    }

    @Override
    public List<DocumentDTO> mapAsListDTO(List<DocumentEntity> entities) {
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentEntity entity:entities){
            documentDTOS.add(mapToDTO(entity));
        }
        return documentDTOS;
    }
}