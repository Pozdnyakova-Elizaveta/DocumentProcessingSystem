package org.example;



public interface DocumentService {
    DocumentDTO createRecord(DocumentDTO document);
    DocumentDTO updateStatus(DocumentDTO document, String status);
}
