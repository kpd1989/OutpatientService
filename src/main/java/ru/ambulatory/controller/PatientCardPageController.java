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

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PatientCardPageController {

    private final PatientCardService patientCardService;
    private final RecipeService recipeService;

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
//                .orElseThrow(() -> new IllegalArgumentException("Non existed patient card"));
        model.addAttribute("recipe", currentRecipe);
        return "card/card";
    }

    @PostMapping("/card/save")
    public String savePatientCard(PatientCardDto card, RecipeDto recipe) {
        patientCardService.save(card);

        if (card.getRecommendations().equals("1")){
            System.out.println(recipe);
            recipeService.save(recipe);
        }
        return "redirect:/cards";
    }
}
