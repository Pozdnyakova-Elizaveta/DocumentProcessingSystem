package org.example.Service;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.example.DocumentDTO;
import org.example.DocumentEntity;
import org.example.repository.DocumentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentsRepository documentsRepository;
    private final MapperFacade mapperFacade = new DefaultMapperFactory.Builder().build().getMapperFacade();
    @Override
    public void createRecord(DocumentDTO document){
        DocumentEntity entity = mapperFacade.map(document, DocumentEntity.class);
        documentsRepository.save(entity);
    }

    @Override
    public List<DocumentEntity> getRecords() {
        return mapperFacade.mapAsList(documentsRepository.findAll(), DocumentEntity.class);
    }
}