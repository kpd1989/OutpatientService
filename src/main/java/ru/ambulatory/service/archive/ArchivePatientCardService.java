package ru.ambulatory.service.archive;

import org.springframework.data.domain.Pageable;
import ru.ambulatory.dto.PatientCardDto;
import ru.ambulatory.dto.archive.ArchivePatientCardPageDto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;

public interface ArchivePatientCardService {
    List<PatientCardDto> findAll();

    ArchivePatientCardPageDto getPage(Pageable pageable);

    Optional<PatientCardDto> getById(@NotEmpty Integer cardId);

    PatientCardDto save(@Valid PatientCardDto card);

    void deleteById(@NotEmpty Integer cardId);
}
