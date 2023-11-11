package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.DocumentDTO;
import org.example.DocumentEntity;
import org.example.DocumentStatus;
import org.example.Service.DocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DocumentController{
    private final DocumentServiceImpl documentService;

    @RequestMapping(path = "/documents", method = RequestMethod.GET)
    private ModelAndView getDocuments(){
        List<DocumentDTO> all = documentService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("documents");
        modelAndView.addObject("documentsFromServer", all);
        return modelAndView;
    }
    @RequestMapping(path = "/documents", method = RequestMethod.POST)
    private String saveDocument(DocumentDTO document){
        DocumentDTO build = DocumentDTO.builder()
                .documentType(document.getDocumentType())
                .date(new Date().toString())
                .organization(document.getOrganization())
                .description(document.getDescription())
                .patient(document.getPatient())
                .status(DocumentStatus.NEW.getStatus()).build();
        documentService.save(build);
        return "redirect:/documents";
    }
}
