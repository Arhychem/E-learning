package Backend.entities;

import Backend.label.MatiereNameLabel;
import Backend.utils.IdWithPrefixeGenerator;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "Chapitre")
public class Chapitre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chap_seq")
    @GenericGenerator(name = "chap_seq",
            strategy = "Backend.utils.IdWithPrefixeGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.VALUE_PREFIX_PARAMETER, value = "chap_"),
                    @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    @Column(name = "chapitreId")
    private String chapitreId;

    @Column(columnDefinition = "varchar(255)")
    private String chapitreName;

    @ManyToOne
    @JoinColumn(name = "matiere_id")
    private Matiere matiere;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chapitre", fetch = FetchType.LAZY)
    private List<Lecon> lecons;

}
