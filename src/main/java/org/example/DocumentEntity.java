package org.example;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;


@Entity
@Table (name = "document")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;
    @Column(length = 100)
    private String documentType;
    @Column(length = 50)
    private String organization;
    @Column
    private String date;
    @Column(length = 100)
    private String description;
    @Column(length = 60)
    private String patient;
    @Column
    private String status;

}
