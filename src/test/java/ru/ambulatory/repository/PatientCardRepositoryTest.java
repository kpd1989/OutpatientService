package ru.ambulatory.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.ambulatory.model.PatientCard;

import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.ambulatory.model.type.Recommendation.TREATMENT_AND_PRESCRIPTION;
import static ru.ambulatory.model.type.SignatureChief.NOT_CONSIDERED;
import static ru.ambulatory.model.type.SignatureDoc.AGREE;

@DisplayName("DAO для работы с картами на основе JDBC должен ")
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PatientCardRepositoryTest {

    @Autowired
    private PatientCardRepository patientCardRepository;

    @DisplayName("уметь получать список всех карт")
    @Test
    public void shouldReturnCorrectAllCardsList() {
        val patientCards = patientCardRepository.findAll();
        assertThat(patientCards).isNotNull().hasSize(6)
                .allMatch(not(s -> s.getId().describeConstable().isEmpty()))
                .allMatch(not(s -> s.getFullName().isEmpty()))
                .allMatch(not(s -> s.getDob().isEmpty()))
                .allMatch(not(s -> s.getNumSite().describeConstable().isEmpty()));
        patientCards.forEach(System.out::println);
    }

    @DisplayName("уметь загружать информацию о конкретной карте по её ID")
    @Test
    public void shouldFindExpectedCardById(){
        Integer cardId = 1;
        val actualCard = patientCardRepository.getOne(cardId);
        assertThat(actualCard).isNotNull()
                .hasFieldOrPropertyWithValue("id", cardId)
                .hasFieldOrPropertyWithValue("fullName", "Сидоров Д.А")
                .hasFieldOrPropertyWithValue("dob", "01.01.2001")
                .hasFieldOrPropertyWithValue("homeAddress", "д.Березовка")
                .hasFieldOrPropertyWithValue("numSite", 1)
                .hasFieldOrPropertyWithValue("complaints", "печень")
                .hasFieldOrPropertyWithValue("anamnesis", "курение")
                .hasFieldOrPropertyWithValue("conditions", "стабильное")
                .hasFieldOrPropertyWithValue("diagnosis", "аллергия")
                .hasFieldOrPropertyWithValue("recommendations", TREATMENT_AND_PRESCRIPTION)
                .hasFieldOrPropertyWithValue("signatureDoc", AGREE)
                .hasFieldOrPropertyWithValue("signatureChief", NOT_CONSIDERED);
    }

    @DisplayName("уметь создавать карточку, а потом загружать информацию о ней")
    @Test
    public void shouldSaveAndLoadCorrectCard() {
        val expectedCard = new PatientCard();
        expectedCard.setId(1);
        expectedCard.setFullName("test");
        expectedCard.setDob("15.08.1989");
        expectedCard.setNumSite(1);
        expectedCard.setRecommendations(TREATMENT_AND_PRESCRIPTION);
        expectedCard.setSignatureDoc(AGREE);
        expectedCard.setSignatureChief(NOT_CONSIDERED);
        val actualCard = patientCardRepository.save(expectedCard);
        assertThat(actualCard).isEqualTo(expectedCard);
    }


    @DisplayName("уметь обновлять  имя пациента и номер поликлиники в БД")
    @Test
    public void shouldUpdateCard() {
        val expectedCard = patientCardRepository.getOne(1);
        assertThat(expectedCard).isNotNull();
        val newName = "test1";
        val newNumSite = 999;
        expectedCard.setFullName(newName);
        expectedCard.setNumSite(newNumSite);
        patientCardRepository.save(expectedCard);
        val actualCard = patientCardRepository.getOne(1);

        assertThat(actualCard).isNotNull()
                .hasFieldOrPropertyWithValue("fullName", newName)
                .hasFieldOrPropertyWithValue("numSite", newNumSite);
    }

    @DisplayName("уметь удалять карточки")
    @Test
    public void shouldDeleteCard() {
        val cardCountBefore = patientCardRepository.findAll().size();
        val newCard = new PatientCard();
        newCard.setId(7);
        newCard.setFullName("test");
        newCard.setDob("15.08.1989");
        newCard.setNumSite(1);
        newCard.setRecommendations(TREATMENT_AND_PRESCRIPTION);
        newCard.setSignatureDoc(AGREE);
        newCard.setSignatureChief(NOT_CONSIDERED);
        val card = patientCardRepository.save(newCard);
        patientCardRepository.delete(card);
        val cardCountAfter = patientCardRepository.findAll().size();

        assertThat(cardCountBefore).isEqualTo(cardCountAfter);
    }
}
