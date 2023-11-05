package org.example;


public class Main {
    private static final DocumentService DOCUMENT_SERVICE = new DocumentServiceImpl();
    public static void main(String[] args) {

        DocumentDTO documentDTO1 = DocumentDTO.builder().documentType("Талон").organization("Поликлиника 1")
                .description("Талон").patient("Сидоров").build();
        documentDTO1 = DOCUMENT_SERVICE.createRecord(documentDTO1);
        System.out.println(documentDTO1);
        DocumentDTO documentDTO2 = DocumentDTO.builder().documentType("Больничный").organization("Поликлиника 2")
                .description("Больничный").patient("Петров").build();
        documentDTO2 = DOCUMENT_SERVICE.createRecord(documentDTO2);
        documentDTO2 = DOCUMENT_SERVICE.updateStatus(documentDTO2, DocumentStatus.ACCEPTED.getStatus());
        System.out.println(documentDTO2);
    }

}