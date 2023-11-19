package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.configuration.JacksonConfiguration;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        var documentDTO = new DocumentDTO();
        documentDTO.setPatient(patient);
        documentDTO.setType(randomAlphabetic(100));
        documentDTO.setOrganization(randomAlphabetic(50));
        documentDTO.setDescription(randomAlphabetic(100));
        mockMvc.perform(postAction(BASE_PATH, documentDTO)).andExpect(status().isOk());
        Mockito.verify(service, Mockito.times(1)).save(documentDTO);
    }

    @Test
    public void errorWhenSaveTest() throws Exception {
        var patient = randomAlphabetic(500);
        when(service.save(any())).thenThrow(new IllegalStateException("Length too long"));
        var documentDTO = new DocumentDTO();
        documentDTO.setId(5L);
        documentDTO.setPatient(patient);
        mockMvc.perform(postAction(BASE_PATH, documentDTO)).andExpect(status().is5xxServerError());
    }

    @Test
    public void getTest() {
        //TODO написать тест
    }

    @Test
    public void sendTest() {
        //TODO написать тест
    }

    @Test
    public void deleteTest() {
        //TODO написать тест
    }

    @Test
    public void deleteAllTest() {
        //TODO написать тест
    }

    private MockHttpServletRequestBuilder postAction(String uri, Object dto) throws JsonProcessingException {
        return post(uri)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto));
    }
}