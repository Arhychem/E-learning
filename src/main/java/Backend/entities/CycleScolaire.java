package Backend.entities;

import Backend.label.CycleNameLabel;
import Backend.utils.IdWithPrefixeGenerator;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@NamedQuery(name = "CycleScolaire.findByName", query = "select c from CycleScolaire c where c.cycleScolaireName=:cycle_name")
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "cycle_scolaire")
public class CycleScolaire {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cy_seq")
    @GenericGenerator(name = "cy_seq",
            strategy = "Backend.utils.IdWithPrefixeGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.VALUE_PREFIX_PARAMETER, value = "cyc_"),
                    @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.NUMBER_FORMAT_PARAMETER, value = "%02d")
            })
    @Column(name = "cycleScolaireId")
    private String cycleScolaireId;

    @Column(columnDefinition = "varchar(255)")
    private String cycleScolaireName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sous_systeme_id")
    private Systeme sousSystemeEducatif;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cycleScolaire", fetch = FetchType.LAZY)
    private List<Niveau> niveaux;
}
