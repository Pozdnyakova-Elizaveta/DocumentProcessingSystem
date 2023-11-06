package org.example.Service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.example.DAO.DocumentDAO;
import org.example.DAO.DocumentDAOImpl;
import org.example.DocumentDTO;
import org.example.DocumentEntity;
import org.example.Service.DocumentService;

import java.util.List;

public class DocumentServiceImpl implements DocumentService {
    private final DocumentDAO dao = new DocumentDAOImpl();
    private final MapperFacade mapperFacade = new DefaultMapperFactory.Builder().build().getMapperFacade();
    @Override
    public DocumentDTO createRecord(DocumentDTO document){
        DocumentEntity documentEntity = dao.createRecord(mapperFacade.map(document, DocumentEntity.class));
        return mapperFacade.map(documentEntity, DocumentDTO.class);
    }

    @Override
    public DocumentDTO updateStatus(DocumentDTO document, String status) {
        DocumentEntity documentEntity = dao.updateStatus(mapperFacade.map(document, DocumentEntity.class), status);
        return mapperFacade.map(documentEntity, DocumentDTO.class);
    }

    @Override
    public List<DocumentDTO> getRecords() {
        List<DocumentEntity> document = dao.getRecords();
        return mapperFacade.mapAsList(document, DocumentDTO.class);
    }
}