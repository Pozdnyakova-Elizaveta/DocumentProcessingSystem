package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс для получения id из тела http-запроса
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdDTO {
    private Long id;
}