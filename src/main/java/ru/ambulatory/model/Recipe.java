package ru.ambulatory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RECIPE")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "preparation")
    private String preparation;

    @Column(name = "status")
//    @Enumerated(value = EnumType.ORDINAL)
    private String status;                            //StatusRecipe

    @Column(name = "note")  //nullable = false
    private String note;

    @OneToOne
    @JoinColumn(name = "card_id")
    private PatientCard patientCard;
}
