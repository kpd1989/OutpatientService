package ru.ambulatory.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.ambulatory.model.PatientCard;
import ru.ambulatory.model.Recipe;

import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.ambulatory.model.type.Recommendation.TREATMENT_AND_PRESCRIPTION;
import static ru.ambulatory.model.type.SignatureChief.NOT_CONSIDERED;
import static ru.ambulatory.model.type.SignatureDoc.AGREE;
import static ru.ambulatory.model.type.Status.OK;
import static ru.ambulatory.model.type.StatusRecipe.ISSUED;
import static ru.ambulatory.model.type.StatusRecipe.NOT_ISSUED;

@DisplayName("DAO для работы с рецептпми на основе JDBC должен ")
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private PatientCardRepository patientCardRepository;

    @DisplayName("уметь получать список всех рецептов")
    @Test
    public void shouldReturnCorrectAllRecipeList() {
        val patientRecipes = recipeRepository.findAll();
        assertThat(patientRecipes).isNotNull().hasSize(3)
                .allMatch(not(s -> s.getNum().describeConstable().isEmpty()))
                .allMatch(not(s -> s.getPreparation().isEmpty()))
                .allMatch(not(s -> s.getStatus().describeConstable().isEmpty()))
                .allMatch(not(s -> s.getNote().isEmpty()))
                .allMatch(not(s -> s.getPatientCard().getNumSite().describeConstable().isEmpty()));
        patientRecipes.forEach(System.out::println);
    }

    @DisplayName("уметь загружать информацию о конкретном рецепте по его ID")
    @Test
    public void shouldFindExpectedRecipeById(){
        Integer recipeId = 1;
        val actualRecipe = recipeRepository.getOne(recipeId);
        assertThat(actualRecipe).isNotNull()
                .hasFieldOrPropertyWithValue("num", recipeId)
                .hasFieldOrPropertyWithValue("preparation", "аскорбинка")
                .hasFieldOrPropertyWithValue("status", NOT_ISSUED)
                .hasFieldOrPropertyWithValue("note", "нет")
                .hasFieldOrPropertyWithValue("patientCard.id", 1);
    }

    @DisplayName("уметь загружать информацию о конкретном рецепте по ID карты пациента")
    @Test
    public void shouldFindExpectedRecipeByCardId(){
        Integer cardId = 2;
        val actualRecipe = recipeRepository.findByCardId(cardId).get();
        assertThat(actualRecipe).isNotNull()
                .hasFieldOrPropertyWithValue("num", 2)
                .hasFieldOrPropertyWithValue("preparation", "витаминка")
                .hasFieldOrPropertyWithValue("status", NOT_ISSUED)
                .hasFieldOrPropertyWithValue("note", "нет")
                .hasFieldOrPropertyWithValue("patientCard.id", cardId);
    }

    @DisplayName("уметь создавать рецепт, а потом загружать информацию о нем")
    @Test
    public void shouldSaveAndLoadCorrectRecipe() {
        val expectedRecipe = new Recipe();
        expectedRecipe.setPreparation("test");
        expectedRecipe.setStatus(NOT_ISSUED);
        expectedRecipe.setNote("test");
        expectedRecipe.setPatientCard(patientCardRepository.getOne(5));
        val actualRecipe = recipeRepository.save(expectedRecipe);
        assertThat(actualRecipe).isEqualTo(expectedRecipe);
    }


    @DisplayName("уметь обновлять  статус рецепта и примечание в БД")
    @Test
    public void shouldUpdateRecipe() {
        val expectedRecipe = recipeRepository.getOne(1);
        assertThat(expectedRecipe).isNotNull();
        val newStatus = ISSUED;
        val newNote = "test999";
        expectedRecipe.setStatus(newStatus);
        expectedRecipe.setNote(newNote);
        recipeRepository.save(expectedRecipe);
        val actualRecipe = recipeRepository.getOne(1);

        assertThat(actualRecipe).isNotNull()
                .hasFieldOrPropertyWithValue("status", newStatus)
                .hasFieldOrPropertyWithValue("note", newNote);
    }

    @DisplayName("уметь удалять рецепты")
    @Test
    public void shouldDeleteRecipe() {
        val recipeCountBefore = recipeRepository.findAll().size();
        val newRecipe = new Recipe();
        newRecipe.setNum(7);
        newRecipe.setPreparation("test");
        newRecipe.setStatus(NOT_ISSUED);
        newRecipe.setNote("test");
        newRecipe.setPatientCard(patientCardRepository.getOne(5));
        val recipe = recipeRepository.save(newRecipe);
        recipeRepository.delete(recipe);
        val recipeCountAfter = recipeRepository.findAll().size();

        assertThat(recipeCountBefore).isEqualTo(recipeCountAfter);
    }
}
