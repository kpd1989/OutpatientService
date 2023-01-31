package ru.ambulatory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ambulatory.model.type.Recommendation;
import ru.ambulatory.model.type.SignatureChief;
import ru.ambulatory.model.type.SignatureDoc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PATIENT_CARD")
public class PatientCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotEmpty
    @Column(name = "dob", nullable = false)
    private String dob;

    @Column(name = "home_address")
    private String homeAddress;

    @NotNull
    @Column(name = "num_site", nullable = false)
    private Integer numSite;

    @Column(name = "complaints")
    private String complaints;

    @Column(name = "anamnesis")
    private String anamnesis;

    @Column(name = "conditions")
    private String conditions;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "recommendations")
    @Enumerated(value = EnumType.ORDINAL)
    private Recommendation recommendations;

    @Column(name = "signature_doc")
    @Enumerated(value = EnumType.ORDINAL)
    private SignatureDoc signatureDoc;

    @Column(name = "signature_chief")
    @Enumerated(value = EnumType.ORDINAL)
    private SignatureChief signatureChief;
}
