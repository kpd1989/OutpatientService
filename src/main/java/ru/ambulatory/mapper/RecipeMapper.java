package ru.ambulatory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ambulatory.dto.RecipeDto;
import ru.ambulatory.model.Recipe;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper
public interface RecipeMapper {

    @Mapping(target = "num", source = "entity.num")
    @Mapping(target = "preparation", source = "entity.preparation")
    @Mapping(target = "status", source = "entity.status")
    @Mapping(target = "note", source = "entity.note")
    @Mapping(target = "patientCard", source = "entity.patientCard")
    RecipeDto toDto(Recipe entity);

    @Mapping(target = "num", source = "dto.num")
    @Mapping(target = "preparation", source = "dto.preparation")
    @Mapping(target = "status", source = "dto.status")
    @Mapping(target = "note", source = "dto.note")
    @Mapping(target = "patientCard", source = "dto.patientCard")
    Recipe toEntity(RecipeDto dto);

    default List<RecipeDto> toDtos(List<Recipe> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    default Optional<RecipeDto> toOptionalDto(Optional<Recipe> entity) {
        return entity.map(this::toDto);
    }
}
