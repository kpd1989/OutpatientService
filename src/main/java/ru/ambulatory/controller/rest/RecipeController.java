package ru.ambulatory.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import ru.ambulatory.dto.RecipePageDto;
import ru.ambulatory.security.Authorities;
import ru.ambulatory.service.RecipeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final Authorities authorities;

    @GetMapping
    public RecipePageDto find(@PageableDefault(value = 20) Pageable pageable) {
        final RecipePageDto page = recipeService.getPage(pageable);
        page.setAdmin(authorities.isAdmin());
        page.setChief(authorities.isChief());
        page.setDoc(authorities.isDoc());
        page.setPharmacy(authorities.isPharmacy());
        return page;
    }

    @DeleteMapping("/{num}")
    public ResponseEntity<?> delete(@PathVariable Integer num) {
        recipeService.deleteById(num);
        return ResponseEntity.noContent().build();
    }

}