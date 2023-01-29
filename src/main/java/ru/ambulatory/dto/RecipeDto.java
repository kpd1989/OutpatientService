package ru.ambulatory.dto;

import lombok.Data;
import ru.ambulatory.model.type.StatusRecipe;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RecipeDto {

    private Integer id;

    private String preparation;

    private String status; //StatusRecipe

    private String note;

    private PatientCardDto patientCard;

    public String getIdStr() {
        return String.format("recipe_%s", id);
    }
}
