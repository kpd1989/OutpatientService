package ru.ambulatory.repository.archive;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.ambulatory.model.archive.ArchivePatientCard;

@PreAuthorize("isAuthenticated()")
public interface ArchivePatientCardRepository extends JpaRepository<ArchivePatientCard, Integer> {


}
