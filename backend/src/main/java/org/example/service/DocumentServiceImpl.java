package org.example.service;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.example.DocumentDTO;
import org.example.DocumentEntity;
import org.example.repository.DocumentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentsRepository documentsRepository;
    private final MapperFacade mapperFacade = new DefaultMapperFactory.Builder().build().getMapperFacade();

    public DocumentDTO save(DocumentDTO documentDTO) {
        DocumentEntity entity = mapperFacade.map(documentDTO, DocumentEntity.class);
        documentsRepository.save(entity);
        return documentDTO;
    }

    public void deleteAll(Set<Long> ids) {
       // documentsRepository.deleteAllById(ids);

    }

    public void delete(Long id) {
        documentsRepository.deleteById(id);
    }

    public DocumentDTO update(DocumentDTO documentDto) {
        delete(documentDto.getId());
        return save(documentDto);
    }

    public List<DocumentDTO> findAll() {
        return mapperFacade.mapAsList(documentsRepository.findAll(), DocumentDTO.class);
    }

    public DocumentDTO get(Long id) {
        return mapperFacade.map(documentsRepository.findById(id), DocumentDTO.class);
    }
}