package org.example.DAO;

import org.example.DocumentDTO;
import org.example.DocumentEntity;

import java.text.ParseException;
import java.util.List;

public interface DocumentDAO {
    void createRecord(DocumentDTO document);
    List<DocumentDTO> getRecords();
}
