package org.example.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.DocumentEntity;
import org.example.DocumentStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DocumentDAOImpl implements DocumentDAO{
    @Override
    public DocumentEntity createRecord(DocumentEntity document){
        EntityManager entityManager = DatabaseConnection.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        document.setStatus(DocumentStatus.NEW.getStatus());
        Date today = new Date();
        SimpleDateFormat date = new SimpleDateFormat("dd MMMM yyyy");
        document.setDate(date.format(today));
        entityManager.persist(document);
        transaction.commit();
        DocumentEntity saved = entityManager.find(DocumentEntity.class, document.getId());
        entityManager.close();
        return saved;
    }

    @Override
    public DocumentEntity updateStatus(DocumentEntity document, String status) {
        EntityManager entityManager = DatabaseConnection.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        DocumentEntity load = entityManager.find(DocumentEntity.class, document.getId());
        load.setStatus(status);
        transaction.commit();
        entityManager.close();
        return load;
    }

    @Override
    public List<DocumentEntity> getRecords() {
        EntityManager entityManager = DatabaseConnection.getEntityManagerFactory().createEntityManager();
        List<DocumentEntity> resultList = entityManager.createQuery("SELECT e FROM DocumentEntity e").getResultList();
        entityManager.close();
        return resultList;
    }
}
