package ru.ambulatory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "dob", nullable = false)
    private String dob;

    @Column(name = "home_address")
    private String homeAddress;

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
   // @Enumerated(value = EnumType.STRING)
    private String recommendations;     //Recommendation

    @Column(name = "signature_doc")
  //  @Enumerated(value = EnumType.STRING)
    private String signatureDoc;      //SignatureDoc

    @Column(name = "signature_chief")
   // @Enumerated(value = EnumType.STRING)
    private String signatureChief;}         //SignatureChief
