package ru.ambulatory.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ambulatory.dto.PatientCardPageDto;
import ru.ambulatory.security.AuthenticationHandler;
import ru.ambulatory.security.Authorities;
import ru.ambulatory.service.PatientCardService;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("REST-контроллер для работы с картами пациентов должен ")
@WebMvcTest(PatientCardController.class)
public class PatientCardControllerTest {

    private static final String CARDS_URI = "/api/cards";

    @Autowired
    protected MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PatientCardService cardService;
    @MockBean
    private AuthenticationHandler authenticationHandler;
    @MockBean
    private Authorities authorities;
    @MockBean
    private UserDetailsService userDetailsService;

    @BeforeEach
    public void init() {
        Mockito.when(cardService.getPage(any())).thenReturn(
                new PatientCardPageDto(Collections.emptyList(), -1, -1, true,true, true, true, true, true, true)
        );
    }

    @Test
    @DisplayName("уметь получать список всех карт")
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void getCards() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(CARDS_URI)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(200);
    }
}
