package ru.ambulatory.repository.archive;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.ambulatory.model.archive.ArchiveRecipe;

@PreAuthorize("isAuthenticated()")
public interface ArchiveRecipeRepository extends JpaRepository<ArchiveRecipe, Integer> {

}
