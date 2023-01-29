package ru.ambulatory.service;

import org.springframework.data.domain.Pageable;
import ru.ambulatory.dto.RecipeDto;
import ru.ambulatory.dto.RecipePageDto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;

public interface RecipeService {
    List<RecipeDto> findAll();

    RecipePageDto getPage(Pageable pageable);

    Optional<RecipeDto> getById(@NotEmpty Integer recipeId);

    Optional<RecipeDto> getByCardId(@NotEmpty Integer cardId);

    RecipeDto save(@Valid RecipeDto recipe);

    void deleteById(@NotEmpty Integer recipeId);
}
