package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.commons.nullanalysis.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.Date;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class DocumentDTO {
    private Long id;
    private String type;
    private String organization;
    private Date date;
    private String description;
    private String patient;
    private Status status;
}
