package org.example;

import lombok.Getter;

@Getter
public enum DocumentStatus {
    NEW ("�����"),
    PROCESSING("� ���������"),
    ACCEPTED("������"),
    REJECTED("��������");
    private final String status;
    DocumentStatus(String status) {
        this.status = status;
    }
}
