package org.example.service;

import org.example.DocumentDTO;
import org.example.DocumentEntity;
import org.example.Status;
import org.example.repository.DocumentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Имплементация сервисов
 */
@Service
public class DocumentServiceImpl implements DocumentService {
    /**
     * Репозиторий для работы с БД
     */
    private final DocumentsRepository documentsRepository;

    @Autowired
    public DocumentServiceImpl(DocumentsRepository documentsRepository) {
        this.documentsRepository = documentsRepository;

    }

    /**
     * Фасад для маппинга DTO и Entity
     */
    private final CustomMapperFacade customMapperFacade = new CustomMapperFacadeImpl();

    @Transactional
    public DocumentDTO save(@Valid DocumentDTO documentDTO) {
        DocumentDTO build = DocumentDTO.builder()
                .type(documentDTO.getType())
                .date(new Date())
                .organization(documentDTO.getOrganization())
                .description(documentDTO.getDescription())
                .patient(documentDTO.getPatient())
                .status(new Status("NEW")).build();
        DocumentEntity entity = customMapperFacade.mapToEntity(build);
        documentsRepository.save(entity);
        return customMapperFacade.mapToDTO(entity);
    }

    @Transactional
    public void deleteAll(Set<Long> ids) {
        for (Long id : ids) {
            documentsRepository.deleteById(id);
        }
    }

    @Transactional
    public void delete(Long id) {
        documentsRepository.deleteById(id);
    }

    @Transactional
    public DocumentDTO update(Long id, String codeStatus) {
        documentsRepository.updateStatus(id, new Status(codeStatus).getName());
        return get(id);
    }

    @Transactional
    public List<DocumentDTO> findAll() {
        List<DocumentEntity> entityList = documentsRepository.findAll();
        return customMapperFacade.mapAsListDTO(entityList);
    }

    @Transactional
    public DocumentDTO get(Long id) {
        DocumentDTO documentDTO;
        Optional<DocumentEntity> entity = documentsRepository.findById(id);
        if (entity.isPresent()) {
            documentDTO = customMapperFacade.mapToDTO(entity.get());
        } else {
            throw new IllegalStateException("Записи с таким id нет!");
        }
        return documentDTO;
    }
}