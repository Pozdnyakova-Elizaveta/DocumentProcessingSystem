package org.example;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;


@Entity
@Table (name = "documents")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 100)
    private String documentType;
    @Column(length = 50)
    private String organization;
    @Column (length = 30)
    private String date;
    @Column(length = 100)
    private String description;
    @Column(length = 60)
    private String patient;
    @Column (length=15)
    private String status;

}
