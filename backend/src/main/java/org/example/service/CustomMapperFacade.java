package org.example.service;

import org.example.DTO.DocumentDTO;
import org.example.entity.DocumentEntity;

import java.util.List;

/**
 * Интерфейс для создания собственного фасада для маппинга
 */
public interface CustomMapperFacade {
    /**
     * Маппинг DTO на Entity
     *
     * @param documentDTO DTO-документ
     * @return Entity-документ
     */
    DocumentEntity mapToEntity(DocumentDTO documentDTO);

    /**
     * Маппинг Entity на DTO
     *
     * @param documentEntity Entity-документ
     * @return DTO-документ
     */
    DocumentDTO mapToDTO(DocumentEntity documentEntity);

    /**
     * Маппинг листа Entity на лсит DTO
     *
     * @param entities лист Entity-документов
     * @return лист DTO-документов
     */
    List<DocumentDTO> mapAsListDTO(List<DocumentEntity> entities);
}