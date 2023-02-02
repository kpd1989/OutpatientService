package ru.ambulatory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ambulatory.dto.RecipeDto;
import ru.ambulatory.service.PatientCardService;
import ru.ambulatory.service.RecipeService;
import ru.ambulatory.service.archive.ArchivePatientCardService;
import ru.ambulatory.service.archive.ArchiveRecipeService;

@Controller
@RequiredArgsConstructor
public class RecipePageController {

    private final RecipeService recipeService;
    private final PatientCardService patientCardService;
    private final ArchivePatientCardService archivePatientCardService;
    private final ArchiveRecipeService archiveRecipeService;

    @GetMapping("/recipes")
    public String allRecipes() {
        return "recipe/recipes";
    }

    @GetMapping("/recipe/add")
    public String newRecipe() {
        return "recipe/recipe";
    }

    @GetMapping("/recipe/edit")
    public String currenRecipe(@RequestParam("num") Integer recipeId, Model model) {
        RecipeDto currentRecipe = recipeService.getById(recipeId)
                .orElseThrow(() -> new IllegalArgumentException("Non existed recipe"));
        model.addAttribute("recipe", currentRecipe);
        return "recipe/recipe";
    }

    @PostMapping("/recipe/save")
    public String saveRecipe(RecipeDto recipe) {

        if (recipe.getStatus().ordinal() == 1) {
            archivePatientCardService.save(recipe.getPatientCard());
            archiveRecipeService.save(recipe);
            recipeService.deleteById(recipe.getNum());
            patientCardService.deleteById(recipe.getPatientCard().getId());
            return "redirect:/recipes";
        }

        recipeService.save(recipe);
        return "redirect:/recipes";
    }
}
