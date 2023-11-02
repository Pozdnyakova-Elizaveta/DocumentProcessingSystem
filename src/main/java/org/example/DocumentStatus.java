package org.example;

import lombok.Getter;

@Getter
public enum DocumentStatus {
    NEW ("Новый"),
    PROCESSING("В обработке"),
    ACCEPTED("Принят"),
    REJECTED("Отклонен");
    private final String status;
    DocumentStatus(String status) {
        this.status = status;
    }
}
