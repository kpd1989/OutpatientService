package ru.ambulatory.dto;

import lombok.Data;
import ru.ambulatory.model.type.Recommendation;
import ru.ambulatory.model.type.SignatureChief;
import ru.ambulatory.model.type.SignatureDoc;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PatientCardDto {
    private Integer id;
    private String fullName;
    private String dob;
    private String homeAddress;
    private Integer numSite;
    private String complaints;
    private String anamnesis;
    private String conditions;
    private String diagnosis;
    private Recommendation recommendations;
    private SignatureDoc signatureDoc;
    private SignatureChief signatureChief;

    public String getIdStr() {
        return String.format("card_%s", id);
    }
}