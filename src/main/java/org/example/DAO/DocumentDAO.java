package org.example.DAO;

import org.example.DocumentEntity;

import java.text.ParseException;

public interface DocumentDAO {
    DocumentEntity createRecord(DocumentEntity document);
    DocumentEntity updateStatus(DocumentEntity document, String status);
}
