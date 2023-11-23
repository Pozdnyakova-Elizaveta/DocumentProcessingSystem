package org.example;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * Сущность документа
 */
@Entity
@Table(name = "documents")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DocumentEntity {
    /**
     * Идентификатор документа
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Вид документа
     */
    @Column(length = 100)
    private String type;
    /**
     * Медицинская организация - владелец документа
     */
    @Column(length = 50)
    private String organization;
    /**
     * Дата создания документа
     */
    @Column
    private Date date;
    /**
     * Описание документа
     */
    @Column(length = 100)
    private String description;
    /**
     * Пациент, к которому относится документ
     */
    @Column(length = 60)
    private String patient;
    /**
     * Статус документа
     */
    @Column(length = 15)
    private String status;

}
