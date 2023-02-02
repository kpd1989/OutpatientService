package ru.ambulatory.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.ambulatory.security.AuthenticationHandler;
import ru.ambulatory.security.Authorities;
import ru.ambulatory.service.PatientCardService;
import ru.ambulatory.service.RecipeService;
import ru.ambulatory.service.archive.ArchivePatientCardService;
import ru.ambulatory.service.archive.ArchiveRecipeService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("Контроллер для работы с картами пациентов должен ")
@WebMvcTest(PatientCardPageController.class)
public class PatientCardPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientCardService patientCardService;
    @MockBean
    private ArchivePatientCardService archivePatientCardService;
    @MockBean
    private ArchiveRecipeService archiveRecipeService;
    @MockBean
    private RecipeService recipeService;
    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    private AuthenticationHandler authenticationHandler;
    @MockBean
    private Authorities authorities;


    @Test
    @DisplayName("уметь получать список всех карт пациентов")
    @WithMockUser(username = "admin", authorities = Authorities.ADMIN)
    public void allCardsShouldReturnMessage() throws Exception {
        this.mockMvc.perform(get("/cards"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Карточки пациентов")));
    }
}
