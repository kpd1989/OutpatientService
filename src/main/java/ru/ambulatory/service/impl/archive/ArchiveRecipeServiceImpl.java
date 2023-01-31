package ru.ambulatory.service.impl.archive;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ambulatory.dto.RecipeDto;
import ru.ambulatory.dto.archive.ArchiveRecipePageDto;
import ru.ambulatory.mapper.ArchiveRecipeMapper;
import ru.ambulatory.model.archive.ArchiveRecipe;
import ru.ambulatory.repository.archive.ArchiveRecipeRepository;
import ru.ambulatory.security.Authorities;
import ru.ambulatory.service.archive.ArchiveRecipeService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArchiveRecipeServiceImpl implements ArchiveRecipeService {

    private final ArchiveRecipeRepository recipeRepository;
    private final ArchiveRecipeMapper recipeMapper;
    private final Authorities authorities;

    @Override
    @Transactional(readOnly = true)
    public List<RecipeDto> findAll() {
        return recipeMapper.toDtos(recipeRepository.findAll());
    }

    @Override
    public ArchiveRecipePageDto getPage(Pageable pageable) {
        Page<ArchiveRecipe> currentPage = recipeRepository.findAll(pageable);
        return new ArchiveRecipePageDto(recipeMapper.toDtos(currentPage.getContent()),
                currentPage.getNumber(),
                currentPage.getTotalPages(),
                currentPage.hasNext(),
                currentPage.hasPrevious(),
                authorities.isAdmin(),
                authorities.isChief(),
                authorities.isDoc(),
                authorities.isPharmacy());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RecipeDto> getById(Integer recipeId) {
        return recipeMapper.toOptionalDto(recipeRepository.findById(recipeId));
    }

    @Override
    @Transactional
    public RecipeDto save(RecipeDto recipe) {
        return recipeMapper.toDto(recipeRepository.save(recipeMapper.toEntity(recipe)));
    }

}
