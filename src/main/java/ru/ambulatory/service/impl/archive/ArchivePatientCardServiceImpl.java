package ru.ambulatory.service.impl.archive;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ambulatory.dto.PatientCardDto;
import ru.ambulatory.dto.archive.ArchivePatientCardPageDto;
import ru.ambulatory.mapper.ArchivePatientCardMapper;
import ru.ambulatory.model.archive.ArchivePatientCard;
import ru.ambulatory.repository.archive.ArchivePatientCardRepository;
import ru.ambulatory.security.Authorities;
import ru.ambulatory.service.archive.ArchivePatientCardService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArchivePatientCardServiceImpl implements ArchivePatientCardService {

    private final ArchivePatientCardRepository patientCardRepository;
    private final ArchivePatientCardMapper patientCardMapper;
    private final Authorities authorities;

    @Override
    @Transactional(readOnly = true)
    public List<PatientCardDto> findAll() {
        return patientCardMapper.toDtos(patientCardRepository.findAll());
    }

    @Override
    public ArchivePatientCardPageDto getPage(Pageable pageable) {
        Page<ArchivePatientCard> currentPage = patientCardRepository.findAll(pageable);
        return new ArchivePatientCardPageDto(patientCardMapper.toDtos(currentPage.getContent()),
                currentPage.getNumber(),
                currentPage.getTotalPages(),
                currentPage.hasNext(),
                currentPage.hasPrevious(),
                authorities.isAdmin(),
                authorities.isChief(),
                authorities.isDoc(),
                authorities.isRegister(),
                authorities.isPharmacy());
    }

    @Override
    @Transactional
    public PatientCardDto save(PatientCardDto patientCard) {
        return patientCardMapper.toDto(patientCardRepository.save(patientCardMapper.toEntity(patientCard)));
    }

}
