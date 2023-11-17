package org.example.service;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.example.DocumentDTO;
import org.example.DocumentEntity;
import org.example.Status;
import org.example.controller.IdsDTO;
import org.example.repository.DocumentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentsRepository documentsRepository;
    private final CustomMapperFacade customMapperFacade = new CustomMapperFacadeImpl();
    @Transactional
    public DocumentDTO save(DocumentDTO documentDTO) {
        DocumentDTO build = DocumentDTO.builder()
                .type(documentDTO.getType())
                .date(new Date())
                .organization(documentDTO.getOrganization())
                .description(documentDTO.getDescription())
                .patient(documentDTO.getPatient())
                .status(Status.ofCode("NEW")).build();
        DocumentEntity entity = customMapperFacade.mapToEntity(build);
        documentsRepository.save(entity);
        return build;
    }
    @Transactional
    public void deleteAll(IdsDTO ids) {
        for (Long id: ids.getIds()) {
            documentsRepository.deleteById(id);
        }
    }
    @Transactional
    public void delete(Long id) {
        documentsRepository.deleteById(id);
    }
    @Transactional
    public DocumentDTO update(DocumentDTO documentDto) {
        documentsRepository.updateStatus(documentDto.getId(), documentDto.getStatus().getName());
        return documentDto;
    }
    @Transactional
    public List<DocumentDTO> findAll() {
        List<DocumentEntity> entityList= documentsRepository.findAll();
        return customMapperFacade.mapAsListDTO(entityList);
    }
    @Transactional
    public DocumentDTO get(Long id) {
        DocumentDTO documentDTO=new DocumentDTO();
        Optional<DocumentEntity> entity = documentsRepository.findById(id);
        if (entity.isPresent()) {
            documentDTO = customMapperFacade.mapToDTO(entity.get());
        }
        return documentDTO;
    }
}