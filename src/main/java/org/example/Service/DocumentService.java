package org.example.Service;


import org.example.DocumentDTO;
import org.example.DocumentEntity;

import java.util.List;

public interface DocumentService {
    DocumentDTO createRecord(DocumentDTO document);
    DocumentDTO updateStatus(DocumentDTO document, String status);
    List<DocumentDTO> getRecords();
}
