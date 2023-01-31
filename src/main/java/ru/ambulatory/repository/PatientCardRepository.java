package ru.ambulatory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.ambulatory.model.PatientCard;

import static ru.ambulatory.security.Authorities.ADMIN;
import static ru.ambulatory.security.Authorities.CHIEF;
import static ru.ambulatory.security.Authorities.DOC;
import static ru.ambulatory.security.Authorities.REGISTER;

@PreAuthorize("isAuthenticated()")
public interface PatientCardRepository extends JpaRepository<PatientCard, Integer> {

    @Secured({ADMIN, CHIEF, DOC, REGISTER})
    PatientCard save(PatientCard card);

    @Secured({ADMIN, CHIEF})
    void delete(PatientCard card);

}
