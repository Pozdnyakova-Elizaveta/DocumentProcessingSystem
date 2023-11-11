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

    @Override
    public DocumentDTO save(DocumentDTO documentDTO) {
        DocumentEntity entity = mapperFacade.map(documentDTO, DocumentEntity.class);
        documentsRepository.save(entity);
        return documentDTO;
    }

    @Override
    public void deleteAll(Set<Long> ids) {
        documentsRepository.deleteAllById(ids);

    }

    @Override
    public void delete(Long id) {
        documentsRepository.deleteById(id);
    }

    @Override
    public DocumentDTO update(DocumentDTO documentDto) {
        delete(documentDto.getId());
        return save(documentDto);
    }

    @Override
    public List<DocumentDTO> findAll() {
        return mapperFacade.mapAsList(documentsRepository.findAll(), DocumentDTO.class);
    }

    @Override
    public DocumentDTO get(Long id) {
        return mapperFacade.map(documentsRepository.findById(id), DocumentDTO.class);
    }
}