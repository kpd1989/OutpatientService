package ru.ambulatory.service.archive;

import org.springframework.data.domain.Pageable;
import ru.ambulatory.dto.RecipeDto;
import ru.ambulatory.dto.archive.ArchiveRecipePageDto;

import javax.validation.Valid;
import java.util.List;

public interface ArchiveRecipeService {
    List<RecipeDto> findAll();

    ArchiveRecipePageDto getPage(Pageable pageable);

    RecipeDto save(@Valid RecipeDto recipe);

}
