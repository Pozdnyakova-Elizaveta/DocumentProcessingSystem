package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.DocumentDTO;
import org.example.Status;
import org.example.annotation.LogMethodInfo;
import org.example.kafka.KafkaSender;
import org.example.service.DocumentService;
import org.example.service.DocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    @Autowired
    private KafkaSender kafkaSender;
    @Autowired
    private DocumentService service;
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @LogMethodInfo
    public DocumentDTO save(@Validated @RequestBody DocumentDTO dto) {
        return service.save(dto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @LogMethodInfo
    public List<DocumentDTO> get() {
        return service.findAll();
    }

    @DeleteMapping(path = "/{id}")
    @LogMethodInfo
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
    @PostMapping(
            path = "send",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @LogMethodInfo
    public DocumentDTO send(@RequestBody IdDTO id) {
        kafkaSender.sendMessage(service.get(id.getId()));
        return service.update(id.getId(), "IN_PROCESS");
    }
    @DeleteMapping
    @LogMethodInfo
    public void deleteAll(@RequestBody IdsDTO idsDto) {
        service.deleteAll(idsDto.getIds());
    }
}