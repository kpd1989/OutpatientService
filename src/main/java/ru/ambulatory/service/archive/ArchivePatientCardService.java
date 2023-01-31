package ru.ambulatory.service.archive;

import org.springframework.data.domain.Pageable;
import ru.ambulatory.dto.PatientCardDto;
import ru.ambulatory.dto.archive.ArchivePatientCardPageDto;

import javax.validation.Valid;
import java.util.List;

public interface ArchivePatientCardService {
    List<PatientCardDto> findAll();

    ArchivePatientCardPageDto getPage(Pageable pageable);

    PatientCardDto save(@Valid PatientCardDto card);

}
