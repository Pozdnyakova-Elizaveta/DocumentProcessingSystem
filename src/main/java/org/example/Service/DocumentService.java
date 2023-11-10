package org.example.Service;


import org.example.DocumentDTO;
import org.example.DocumentEntity;

import java.util.List;

public interface DocumentService {
    void createRecord(DocumentDTO document);
    List<DocumentEntity> getRecords();
}
