package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Класс для получения списка id из http-запроса
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdsDTO {
    private Set<Long> ids;
}
