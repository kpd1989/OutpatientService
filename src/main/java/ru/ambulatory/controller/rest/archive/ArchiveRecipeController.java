package ru.ambulatory.controller.rest.archive;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ambulatory.dto.RecipePageDto;
import ru.ambulatory.dto.archive.ArchiveRecipePageDto;
import ru.ambulatory.security.Authorities;
import ru.ambulatory.service.RecipeService;
import ru.ambulatory.service.archive.ArchiveRecipeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/archive_recipes")
public class ArchiveRecipeController {

    private final ArchiveRecipeService recipeService;
    private final Authorities authorities;

    @GetMapping
    public ArchiveRecipePageDto find(@PageableDefault(value = 20) Pageable pageable) {
        final ArchiveRecipePageDto page = recipeService.getPage(pageable);
        page.setAdmin(authorities.isAdmin());
        page.setChief(authorities.isChief());
        page.setPharmacy(authorities.isPharmacy());
        return page;
    }

}