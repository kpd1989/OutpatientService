package ru.ambulatory.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ambulatory.dto.PatientCardDto;
import ru.ambulatory.dto.RecipeDto;
import ru.ambulatory.dto.RecipePageDto;
import ru.ambulatory.mapper.PatientCardMapper;
import ru.ambulatory.mapper.RecipeMapper;
import ru.ambulatory.model.Recipe;
import ru.ambulatory.repository.PatientCardRepository;
import ru.ambulatory.repository.RecipeRepository;
import ru.ambulatory.security.Authorities;
import ru.ambulatory.service.RecipeService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;
    private final PatientCardRepository patientCardRepository;
    private final PatientCardMapper patientCardMapper;
    private final Authorities authorities;

    @Override
    @Transactional(readOnly = true)
    public List<RecipeDto> findAll() {
        return recipeMapper.toDtos(recipeRepository.findAll());
    }

    @Override
    public RecipePageDto getPage(Pageable pageable) {
        Page<Recipe> currentPage = recipeRepository.findAll(pageable);
        return new RecipePageDto(recipeMapper.toDtos(currentPage.getContent()),
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
    public Optional<RecipeDto> getByCardId(Integer cardId) {
        return recipeMapper.toOptionalDto(recipeRepository.findByCardId(cardId));
    }

    @Override
    @Transactional
    public RecipeDto save(RecipeDto recipe) {
        return recipeMapper.toDto(recipeRepository.save(recipeMapper.toEntity(recipe)));
    }

    @Override
    @Transactional
    public void deleteById(Integer recipeId) {
        recipeRepository.deleteById(recipeId);
    }
}
