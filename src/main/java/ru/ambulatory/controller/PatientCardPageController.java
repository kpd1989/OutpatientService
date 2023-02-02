package ru.ambulatory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ambulatory.dto.PatientCardDto;
import ru.ambulatory.dto.RecipeDto;
import ru.ambulatory.model.type.SignatureChief;
import ru.ambulatory.service.PatientCardService;
import ru.ambulatory.service.RecipeService;
import ru.ambulatory.service.archive.ArchivePatientCardService;
import ru.ambulatory.service.archive.ArchiveRecipeService;

import static ru.ambulatory.model.type.Recommendation.NOT_SELECTED;
import static ru.ambulatory.model.type.Recommendation.TREATMENT_AND_PRESCRIPTION;
import static ru.ambulatory.model.type.Recommendation.NO_RECOMMENDATIONS;
import static ru.ambulatory.model.type.Recommendation.TO_ANOTHER_DOCTOR;
import static ru.ambulatory.model.type.Recommendation.TO_INSPECTION;
import static ru.ambulatory.model.type.SignatureDoc.AGREE;
import static ru.ambulatory.model.type.SignatureDoc.NO_ACTUAL;
import static ru.ambulatory.model.type.StatusRecipe.ISSUED;


@Controller
@RequiredArgsConstructor
public class PatientCardPageController {

    private final PatientCardService patientCardService;
    private final ArchivePatientCardService archivePatientCardService;
    private final RecipeService recipeService;
    private final ArchiveRecipeService archiveRecipeService;

    @GetMapping("/cards")
    public String allCards() {
        return "card/cards";
    }

    @GetMapping("/card/add")
    public String newPatientCard() {
        return "card/card";
    }

    @GetMapping("/card/edit")
    public String currentPatientCard(@RequestParam("id") Integer cardId, Model model) {
        PatientCardDto currentPatientCard = patientCardService.getById(cardId)
                .orElseThrow(() -> new IllegalArgumentException("Non existed patient card with id=" + cardId));
        model.addAttribute("patientCard", currentPatientCard);

        RecipeDto currentRecipe = recipeService.getByCardId(cardId)
                .orElse(new RecipeDto());
        model.addAttribute("recipe", currentRecipe);
        return "card/card";
    }

    @PostMapping("/card/save")
    public String savePatientCard(PatientCardDto card, RecipeDto recipe) {

        if (((card.getRecommendations().equals(NO_RECOMMENDATIONS)
              || card.getRecommendations().equals(TO_INSPECTION)
              || card.getRecommendations().equals(TO_ANOTHER_DOCTOR)
              || card.getRecommendations().equals(NOT_SELECTED))
             && card.getSignatureDoc().equals(AGREE))
            || card.getSignatureDoc().equals(NO_ACTUAL)) {
            archivePatientCardService.save(card);
            patientCardService.deleteById(card.getId());
            return "redirect:/cards";
        }

        if (card.getSignatureChief().equals(SignatureChief.AGREE)
            && recipe.getStatus().equals(ISSUED)) {
            archivePatientCardService.save(card);
            archiveRecipeService.save(recipe);
            recipeService.deleteById(recipe.getNum());
            patientCardService.deleteById(card.getId());
            return "redirect:/cards";
        }

        if (card.getRecommendations().equals(TREATMENT_AND_PRESCRIPTION)) {
            recipeService.save(recipe);
        }
        patientCardService.save(card);

        return "redirect:/cards";
    }
}
