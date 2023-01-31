package ru.ambulatory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ambulatory.dto.PatientCardDto;
import ru.ambulatory.dto.RecipeDto;
import ru.ambulatory.service.PatientCardService;
import ru.ambulatory.service.RecipeService;
import ru.ambulatory.service.archive.ArchivePatientCardService;
import ru.ambulatory.service.archive.ArchiveRecipeService;


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
                .orElseThrow(() -> new IllegalArgumentException("Non existed patient card"));
        model.addAttribute("patientCard", currentPatientCard);

        RecipeDto currentRecipe = recipeService.getByCardId(cardId)
                .orElse(new RecipeDto());
        model.addAttribute("recipe", currentRecipe);
        return "card/card";
    }

    @PostMapping("/card/save")
    public String savePatientCard(PatientCardDto card, RecipeDto recipe) {

        if ((card.getRecommendations().ordinal() >= 2 && card.getSignatureDoc().ordinal() == 1) ||
            card.getSignatureDoc().ordinal() == 2) {
            archivePatientCardService.save(card);
            patientCardService.deleteById(card.getId());
            return "redirect:/cards";
        }

        if (card.getSignatureChief().ordinal() == 1 && recipe.getStatus().ordinal() == 1) {
            archivePatientCardService.save(card);
            archiveRecipeService.save(recipe);
            patientCardService.deleteById(card.getId());
            recipeService.deleteById(recipe.getNum());
            return "redirect:/cards";
        }

        if (card.getRecommendations().ordinal() == 1) {
            recipeService.save(recipe);
        }
        patientCardService.save(card);


        return "redirect:/cards";
    }
}
