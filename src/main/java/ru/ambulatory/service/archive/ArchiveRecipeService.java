package ru.ambulatory.service.archive;

import org.springframework.data.domain.Pageable;
import ru.ambulatory.dto.RecipeDto;
import ru.ambulatory.dto.archive.ArchiveRecipePageDto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;

public interface ArchiveRecipeService {
    List<RecipeDto> findAll();

    ArchiveRecipePageDto getPage(Pageable pageable);

    Optional<RecipeDto> getById(@NotEmpty Integer recipeId);


    RecipeDto save(@Valid RecipeDto recipe);

}
