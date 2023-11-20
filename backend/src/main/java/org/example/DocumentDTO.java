package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.commons.nullanalysis.NotNull;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.Date;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    private Long id;
    @NotBlank
    private String type;
    @NotBlank
    private String organization;
    private Date date;
    @NotBlank
    private String description;
    @NotBlank
    private String patient;
    private Status status;
}
