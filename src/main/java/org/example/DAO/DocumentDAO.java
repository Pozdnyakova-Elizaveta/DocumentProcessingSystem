package org.example.DAO;

import org.example.DocumentEntity;

import java.text.ParseException;
import java.util.List;

public interface DocumentDAO {
    DocumentEntity createRecord(DocumentEntity document);
    DocumentEntity updateStatus(DocumentEntity document, String status);
    List<DocumentEntity> getRecords();
}
