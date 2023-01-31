package ru.ambulatory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ambulatory.model.type.StatusRecipe;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Enumerated;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RECIPE")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private Integer num;

    @Column(name = "preparation")
    private String preparation;

    @Column(name = "status")
    @Enumerated(value = EnumType.ORDINAL)
    private StatusRecipe status;

    @Column(name = "note")  //nullable = false
    private String note;

    @OneToOne
    @JoinColumn(name = "card_id")
    private PatientCard patientCard;
}
