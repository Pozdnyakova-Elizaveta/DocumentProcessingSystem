package org.example;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.example.DAO.DocumentDAO;
import org.example.DAO.DocumentDAOImpl;

public class DocumentServiceImpl implements DocumentService {
    private final DocumentDAO dao = new DocumentDAOImpl();
    private final MapperFacade mapperFacade = new DefaultMapperFactory.Builder().build().getMapperFacade();
    @Override
    public DocumentDTO createRecord(DocumentDTO document){
        DocumentEntity student = dao.createRecord(mapperFacade.map(document, DocumentEntity.class));
        return mapperFacade.map(student, DocumentDTO.class);
    }

    @Override
    public DocumentDTO updateStatus(DocumentDTO document, String status) {
        DocumentEntity student = dao.updateStatus(mapperFacade.map(document, DocumentEntity.class), status);
        return mapperFacade.map(student, DocumentDTO.class);
    }
}