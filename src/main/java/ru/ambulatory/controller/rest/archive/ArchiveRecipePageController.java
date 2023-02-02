package ru.ambulatory.controller.rest.archive;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ArchiveRecipePageController {

    @GetMapping("/archive_recipes")
    public String allRecipes() {
        return "archive/archive_recipes";
    }

}
