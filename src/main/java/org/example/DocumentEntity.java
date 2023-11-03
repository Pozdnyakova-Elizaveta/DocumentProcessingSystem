package org.example;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table (name = "document")
@NoArgsConstructor
@Data
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Integer id;
    @Column(length = 100)
    private String documentType;
    @Column(length = 50)
    private String organization;
    @Column
    private Date date;
    @Column(length = 100)
    private String description;
    @Column(length = 60)
    private String patient;
    @Column
    private String status;

}
