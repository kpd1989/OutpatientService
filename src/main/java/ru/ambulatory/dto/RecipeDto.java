package ru.ambulatory.dto;

import lombok.Data;
import ru.ambulatory.model.type.StatusRecipe;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RecipeDto {

    private Integer num;

    private String preparation;

    private StatusRecipe status;

    private String note;

    private PatientCardDto patientCard;

    public String getNumStr() {
        return String.format("recipe_%s", num);
    }
}
