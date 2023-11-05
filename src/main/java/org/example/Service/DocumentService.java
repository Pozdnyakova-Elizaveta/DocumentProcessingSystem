package org.example.Service;


import org.example.DocumentDTO;

public interface DocumentService {
    DocumentDTO createRecord(DocumentDTO document);
    DocumentDTO updateStatus(DocumentDTO document, String status);
}
