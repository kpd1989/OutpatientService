package ru.ambulatory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ambulatory.dto.PatientCardDto;
import ru.ambulatory.model.archive.ArchivePatientCard;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper
public interface ArchivePatientCardMapper {

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "fullName", source = "entity.fullName")
    @Mapping(target = "dob", source = "entity.dob")
    @Mapping(target = "homeAddress", source = "entity.homeAddress")
    @Mapping(target = "numSite", source = "entity.numSite")
    @Mapping(target = "complaints", source = "entity.complaints")
    @Mapping(target = "anamnesis", source = "entity.anamnesis")
    @Mapping(target = "conditions", source = "entity.conditions")
    @Mapping(target = "diagnosis", source = "entity.diagnosis")
    @Mapping(target = "recommendations", source = "entity.recommendations")
    @Mapping(target = "signatureDoc", source = "entity.signatureDoc")
    @Mapping(target = "signatureChief", source = "entity.signatureChief")
    PatientCardDto toDto(ArchivePatientCard entity);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "fullName", source = "dto.fullName")
    @Mapping(target = "dob", source = "dto.dob")
    @Mapping(target = "homeAddress", source = "dto.homeAddress")
    @Mapping(target = "numSite", source = "dto.numSite")
    @Mapping(target = "complaints", source = "dto.complaints")
    @Mapping(target = "anamnesis", source = "dto.anamnesis")
    @Mapping(target = "conditions", source = "dto.conditions")
    @Mapping(target = "diagnosis", source = "dto.diagnosis")
    @Mapping(target = "recommendations", source = "dto.recommendations")
    @Mapping(target = "signatureDoc", source = "dto.signatureDoc")
    @Mapping(target = "signatureChief", source = "dto.signatureChief")
    ArchivePatientCard toEntity(PatientCardDto dto);

    default List<PatientCardDto> toDtos(List<ArchivePatientCard> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    default Optional<PatientCardDto> toOptionalDto(Optional<ArchivePatientCard> entity) {
        return entity.map(this::toDto);
    }
}
