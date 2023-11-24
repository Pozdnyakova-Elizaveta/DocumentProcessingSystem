package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.DTO.DocumentDTO;
import org.example.DTO.Status;
import org.example.configuration.JacksonConfiguration;
import org.example.DTO.IdDTO;
import org.example.DTO.IdsDTO;
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

/**
 * Тестирование контроллера
 */
@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class DocumentControllerTest {
    /**
     * Основной путь для обработки запросов
     */
    private static final String BASE_PATH = "/documents";
    /**
     * Маппер JSON и java-объектов
     */
    private final ObjectMapper mapper = new JacksonConfiguration().objectMapper();
    /**
     * Объект для для тестирования обработки запросов
     */
    private MockMvc mockMvc;
    /**
     * Mockito-имитация сервисов
     */
    @MockBean
    private DocumentServiceImpl service;
    /**
     * Конфигурация для веб-приложения
     */
    private final WebApplicationContext webApplicationContext;

    @Autowired
    DocumentControllerTest(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;

    }

    /**
     * DTO-объект документа для тестирования
     */
    private final DocumentDTO documentDTO = DocumentDTO.builder().patient(randomAlphabetic(20))
            .description(randomAlphabetic(20)).type(randomAlphabetic(20)).
            organization(randomAlphabetic(20)).id(1L).status(new Status("NEW")).build();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    /**
     * Тест успешного добавления документа
     *
     * @throws Exception если произошла ошибка при выполнении postAction
     *                   или неправильно заданы параметры для метода perform
     */
    @Test
    public void successWhenSaveTest() throws Exception {
        var patient = randomAlphabetic(60);
        when(service.save(any())).thenReturn(any());
        documentDTO.setPatient(patient);
        mockMvc.perform(postAction(BASE_PATH, documentDTO)).andExpect(status().isOk());
        Mockito.verify(service, Mockito.times(1)).save(documentDTO);
    }

    /**
     * Тест неудачной попытки сохранения с длиной строки большей длины поля
     *
     * @throws Exception если произошла ошибка при выполнении postAction
     *                   или неправильно заданы параметры для метода perform
     */
    @Test
    public void errorWhenSaveTest() throws Exception {
        var patient = randomAlphabetic(500);
        when(service.save(any())).thenThrow(new IllegalStateException("Length too long"));
        documentDTO.setPatient(patient);
        mockMvc.perform(postAction(BASE_PATH, documentDTO)).andExpect(status().is5xxServerError());
    }

    /**
     * Тест получения всех документов
     *
     * @throws Exception если неправильно заданы параметры для метода perform
     */
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

    /**
     * Тест отправки документа в обработку
     *
     * @throws Exception если произошла ошибка при выполнении postAction
     *                   или неправильно заданы параметры для метода perform
     */
    @Test
    public void sendTest() throws Exception {
        when(service.update(documentDTO.getId(), "IN_PROCESS")).thenAnswer(e -> {
            documentDTO.setStatus(new Status("IN_PROCESS"));
            return documentDTO;
        });
        mockMvc.perform(postAction(BASE_PATH + "/send", new IdDTO(documentDTO.getId()))).andExpect(status().isOk())
                .andExpect(jsonPath("$.status.code").value("IN_PROCESS"));
    }

    /**
     * Тест удаления документа
     *
     * @throws Exception если произошла ошибка при выполнении deleteActionId
     *                   или неправильно заданы параметры для метода perform
     */
    @Test
    public void deleteTest() throws Exception {
        doNothing().when(service).delete(documentDTO.getId());
        mockMvc.perform(deleteActionId(documentDTO.getId())).andExpect(status().isOk());
        Mockito.verify(service, Mockito.times(1)).delete(documentDTO.getId());
    }

    /**
     * Тест удаления нескольких документов
     *
     * @throws Exception если произошла ошибка при выполнении deleteActionIdsDTO
     *                   или неправильно заданы параметры для метода perform
     */
    @Test
    public void deleteAllTest() throws Exception {
        IdsDTO id = new IdsDTO(Set.of(1L, 2L));
        doNothing().when(service).deleteAll(id.getIds());
        mockMvc.perform(deleteActionIdsDTO(id)).andExpect(status().isOk());
        Mockito.verify(service, Mockito.times(1)).deleteAll(id.getIds());
    }

    /**
     * Метод для создания POST-запроса
     *
     * @param uri    адрес запроса
     * @param object объект, преобразуемый в JSON
     * @return созданный запрос
     * @throws JsonProcessingException если JSON имеет неверный синтаксис или возникли ошибки при сериализации
     */
    private MockHttpServletRequestBuilder postAction(String uri, Object object) throws JsonProcessingException {
        return post(uri)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(object));
    }

    /**
     * Метод для создания DELETE-запроса для получения IdsDTO
     *
     * @param id объект, преобразуемый в JSON
     * @return созданный запрос
     * @throws JsonProcessingException если JSON имеет неверный синтаксис или возникли ошибки при сериализации
     */
    private MockHttpServletRequestBuilder deleteActionIdsDTO(Object id) throws JsonProcessingException {
        return delete(DocumentControllerTest.BASE_PATH)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(id));
    }

    /**
     * Метод для создания DELETE-запроса для получения Id
     *
     * @param id объект, преобразуемый в JSON
     * @return созданный запрос
     * @throws JsonProcessingException если JSON имеет неверный синтаксис или возникли ошибки при сериализации
     */
    private MockHttpServletRequestBuilder deleteActionId(Object id) throws JsonProcessingException {
        return delete(DocumentControllerTest.BASE_PATH + "/{id}", id)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(id));
    }
}