package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.DocumentDTO;
import org.example.DocumentStatus;
import org.example.annotation.LogMethodInfo;
import org.example.service.DocumentService;
import org.example.service.DocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    @Autowired
    private DocumentService service;
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @LogMethodInfo
    public DocumentDTO save(@RequestBody DocumentDTO dto) {
        DocumentDTO build = DocumentDTO.builder()
                .documentType(dto.getDocumentType())
                .date(new Date())
                .organization(dto.getOrganization())
                .description(dto.getDescription())
                .patient(dto.getPatient())
                .status(DocumentStatus.NEW.getStatus()).build();
        return service.save(build);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @LogMethodInfo
    public List<DocumentDTO> get() {
        return service.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}