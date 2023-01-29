package ru.ambulatory.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ambulatory.dto.PatientCardDto;
import ru.ambulatory.dto.PatientCardPageDto;
import ru.ambulatory.mapper.PatientCardMapper;
import ru.ambulatory.model.PatientCard;
import ru.ambulatory.repository.PatientCardRepository;
import ru.ambulatory.security.Authorities;
import ru.ambulatory.service.PatientCardService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientCardServiceImpl implements PatientCardService {

    private final PatientCardRepository patientCardRepository;
    private final PatientCardMapper patientCardMapper;
    private final Authorities authorities;

    @Override
    @Transactional(readOnly = true)
    public List<PatientCardDto> findAll() {
        return patientCardMapper.toDtos(patientCardRepository.findAll());
    }

    @Override
    public PatientCardPageDto getPage(Pageable pageable) {
        Page<PatientCard> currentPage = patientCardRepository.findAll(pageable);
        return new PatientCardPageDto(patientCardMapper.toDtos(currentPage.getContent()),
                currentPage.getNumber(),
                currentPage.getTotalPages(),
                currentPage.hasNext(),
                currentPage.hasPrevious(),
                authorities.isAdmin(),
                authorities.isDoc(),
                authorities.isRegister(),
                authorities.isPharmacy());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PatientCardDto> getById(Integer cardId) {
        return patientCardMapper.toOptionalDto(patientCardRepository.findById(cardId));
    }

    @Override
    @Transactional
    public PatientCardDto save(PatientCardDto patientCard) {
        return patientCardMapper.toDto(patientCardRepository.save(patientCardMapper.toEntity(patientCard)));
    }

    @Override
    @Transactional
    public void deleteById(Integer cardId) {
        patientCardRepository.deleteById(cardId);
    }
}
