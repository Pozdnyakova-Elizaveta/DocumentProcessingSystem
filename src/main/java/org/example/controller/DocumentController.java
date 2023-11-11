package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.DocumentDTO;
import org.example.DocumentStatus;
import org.example.Service.DocumentServiceImpl;
import org.example.annotation.LogMethodInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DocumentController{
    private final DocumentServiceImpl documentService;
    @LogMethodInfo
    @RequestMapping(path = "/documents", method = RequestMethod.GET)
    private ModelAndView getDocuments(){
        List<DocumentDTO> all = documentService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("documents");
        modelAndView.addObject("documentsFromServer", all);
        return modelAndView;
    }
    @LogMethodInfo
    @RequestMapping(path = "/documents", method = RequestMethod.POST)
    private String saveDocument(DocumentDTO document){
        DocumentDTO build = DocumentDTO.builder()
                .documentType(document.getDocumentType())
                .date(new SimpleDateFormat("d MMMM yyyy HH:mm").format(new Date()))
                .organization(document.getOrganization())
                .description(document.getDescription())
                .patient(document.getPatient())
                .status(DocumentStatus.NEW.getStatus()).build();
        documentService.save(build);
        return "redirect:/documents";
    }
}
