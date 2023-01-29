package ru.ambulatory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.ambulatory.model.Recipe;

import java.util.Optional;

import static ru.ambulatory.security.Authorities.*;

@PreAuthorize("isAuthenticated()")
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Secured({ADMIN, DOC, PHARMACY})
    Recipe save(Recipe recipe);

    @Secured(ADMIN)
    void delete(Recipe recipe);

    @Query("select resepi from Recipe resepi " +
           "where resepi.patientCard.id = :cardId")
    @Secured({ADMIN, DOC, PHARMACY})
    Optional<Recipe> findByCardId(@Param("cardId") Integer cardId);
}
