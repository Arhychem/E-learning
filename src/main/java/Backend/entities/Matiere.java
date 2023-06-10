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
@Table(name = "matiere")
public class Matiere {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matiere_seq")
    @GenericGenerator(name = "matiere_seq",
            strategy = "Backend.utils.IdWithPrefixeGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.VALUE_PREFIX_PARAMETER, value = "mat_"),
                    @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.NUMBER_FORMAT_PARAMETER, value = "%02d")
            })
    @Column(name = "matiereId")
    private String matiereId;

    @Column(columnDefinition = "varchar(255)")
    private String matiereName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "niveau_id")
    private Niveau niveau;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matiere", fetch = FetchType.LAZY)
    private List<Chapitre> chapitres;
}
