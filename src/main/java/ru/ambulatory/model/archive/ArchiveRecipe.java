package ru.ambulatory.model.archive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ambulatory.model.type.StatusRecipe;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ARCHIVE_RECIPE")
public class ArchiveRecipe {

    @Id
    @Column(name = "num")
    private Integer num;

    @Column(name = "preparation")
    private String preparation;

    @Column(name = "status")
    @Enumerated(value = EnumType.ORDINAL)
    private StatusRecipe status;

    @Column(name = "note")
    private String note;

    @OneToOne
    @JoinColumn(name = "card_id")
    private ArchivePatientCard patientCard;
}
