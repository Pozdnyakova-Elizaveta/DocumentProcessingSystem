package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * DTO документа
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    /**
     * Идентификатор документа
     */
    private Long id;
    /**
     * Вид документа
     */
    @NotBlank
    private String type;
    /**
     * Медицинская организация - владелец документа
     */
    @NotBlank
    private String organization;
    /**
     * Дата создания документа
     */
    private Date date;
    /**
     * Описание документа
     */
    @NotBlank
    private String description;
    /**
     * Пациент, к которому относится документ
     */
    @NotBlank
    private String patient;
    /**
     * Объект статуса
     */
    private Status status;
}
