package org.example;


public class Main {
    private static final DocumentService DOCUMENT_SERVICE = new DocumentServiceImpl();
    public static void main(String[] args) {

        DocumentDTO documentDTO1 = DocumentDTO.builder().documentType("�����").organization("����������� 1")
                .description("�����").patient("�������").build();
        documentDTO1 = DOCUMENT_SERVICE.createRecord(documentDTO1);
        System.out.println(documentDTO1);
        DocumentDTO documentDTO2 = DocumentDTO.builder().documentType("����������").organization("����������� 2")
                .description("����������").patient("������").build();
        documentDTO2 = DOCUMENT_SERVICE.createRecord(documentDTO2);
        documentDTO2 = DOCUMENT_SERVICE.updateStatus(documentDTO2, DocumentStatus.ACCEPTED.getStatus());
        System.out.println(documentDTO2);
    }

}