package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.security.oauthbearer.secured.ValidateException;
import org.example.configuration.JacksonConfiguration;
import org.example.controller.IdDTO;
import org.example.controller.IdsDTO;
import org.example.service.DocumentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith({ SpringExtension.class, MockitoExtension.class })
public class DocumentControllerTest {
    private static final String BASE_PATH = "/documents";

    private final ObjectMapper mapper = new JacksonConfiguration().objectMapper();
    private MockMvc mockMvc;
    @MockBean
    private DocumentServiceImpl service;

    @Autowired
    private WebApplicationContext webApplicationContext;
    DocumentDTO documentDTO = DocumentDTO.builder().patient(randomAlphabetic(20))
            .description(randomAlphabetic(20)).type(randomAlphabetic(20)).
            organization(randomAlphabetic(20)).id(1L).status(Status.ofCode("NEW")).build();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void successWhenSaveTest() throws Exception {
        var patient = randomAlphabetic(60);
        when(service.save(any())).thenReturn(any());
        documentDTO.setPatient(patient);
        mockMvc.perform(postAction(BASE_PATH, documentDTO)).andExpect(status().isOk());
        Mockito.verify(service, Mockito.times(1)).save(documentDTO);
    }

    @Test
    public void errorWhenSaveTest() throws Exception {
        var patient = randomAlphabetic(500);
        when(service.save(any())).thenThrow(new IllegalStateException("Length too long"));
        documentDTO.setPatient(patient);
        mockMvc.perform(postAction(BASE_PATH, documentDTO)).andExpect(status().is5xxServerError());
    }

    @Test
    public void getTest() throws Exception {
        List<DocumentDTO> documentsDTO = new ArrayList<>();
        documentsDTO.add(new DocumentDTO());
        documentsDTO.add(new DocumentDTO());
        when(service.findAll()).thenReturn(documentsDTO);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH))
                .andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(documentsDTO.size()));
        Mockito.verify(service, Mockito.times(1)).findAll();
    }

    @Test
    public void sendTest() throws Exception {
        when(service.update(documentDTO.getId(),"IN_PROCESS")).thenAnswer(e->{
            documentDTO.setStatus(Status.ofCode("IN_PROCESS"));
            return documentDTO;
        });
        mockMvc.perform(postAction(BASE_PATH+"/send", new IdDTO(documentDTO.getId()))).andExpect(status().isOk())
                .andExpect(jsonPath("$.status.code").value("IN_PROCESS"));
    }

    @Test
    public void deleteTest() throws Exception {
        doNothing().when(service).delete(documentDTO.getId());
        mockMvc.perform(deleteActionId(BASE_PATH, documentDTO.getId())).andExpect(status().isOk());
        Mockito.verify(service, Mockito.times(1)).delete(documentDTO.getId());
    }

    @Test
    public void deleteAllTest() throws Exception {
        IdsDTO id= new IdsDTO(Set.of(1L,2L));
        doNothing().when(service).deleteAll(id.getIds());
        mockMvc.perform(deleteActionIdsDTO(BASE_PATH, id)).andExpect(status().isOk());
        Mockito.verify(service, Mockito.times(1)).deleteAll(id.getIds());
    }

    private MockHttpServletRequestBuilder postAction(String uri, Object object) throws JsonProcessingException {
        return post(uri)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(object));
    }
    private MockHttpServletRequestBuilder deleteActionIdsDTO(String uri, Object id) throws JsonProcessingException {
        return delete(uri)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(id));
    }
    private MockHttpServletRequestBuilder deleteActionId(String uri, Object id) throws JsonProcessingException {
        return delete(uri+"/{id}",id)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(id));
    }
}