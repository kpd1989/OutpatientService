package ru.ambulatory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.ambulatory.model.Recipe;

import java.util.Optional;

import static ru.ambulatory.security.Authorities.ADMIN;
import static ru.ambulatory.security.Authorities.CHIEF;
import static ru.ambulatory.security.Authorities.DOC;
import static ru.ambulatory.security.Authorities.PHARMACY;

@PreAuthorize("isAuthenticated()")
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Secured({ADMIN, CHIEF, DOC, PHARMACY})
    Recipe save(Recipe recipe);

    @Secured({ADMIN, CHIEF})
    void delete(Recipe recipe);

    @Query("select resepi from Recipe resepi " +
           "where resepi.patientCard.id = :cardId")
    @Secured({ADMIN, CHIEF, DOC, PHARMACY})
    Optional<Recipe> findByCardId(@Param("cardId") Integer cardId);
}
