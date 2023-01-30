package ru.ambulatory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ambulatory.dto.RecipeDto;
import ru.ambulatory.service.RecipeService;

@Controller
@RequiredArgsConstructor
public class RecipePageController {

    private final RecipeService recipeService;

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
        recipeService.save(recipe);
        return "redirect:/recipes";
    }
}
