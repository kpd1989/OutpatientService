package ru.ambulatory.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class PatientCardDto {

    private Integer id;

    @NotNull
    private String fullName;

    @NotNull
    private String dob;

    private String homeAddress;

    @NotNull
    private Integer numSite;

    private String complaints;

    private String anamnesis;

    private String conditions;

    private String diagnosis;

    private String recommendations;     //Recommendation

    private String signatureDoc;           //SignatureDoc

    private String signatureChief;         // SignatureChief

    public String getIdStr() {
        return String.format("card_%s", id);
    }
}