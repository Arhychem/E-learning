package Backend.entities;


import Backend.label.NiveauNameLabel;
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
@Table(name = "niveau")
public class Niveau {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "niv_seq")
    @GenericGenerator(name = "niv_seq",
            strategy = "Backend.utils.IdWithPrefixeGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.VALUE_PREFIX_PARAMETER, value = "niv_"),
                    @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.NUMBER_FORMAT_PARAMETER, value = "%02d")
            })
    @Column(name = "niveauId")
    private String niveauId;

    @Column(columnDefinition = "varchar(255)")
    private NiveauNameLabel niveauName;

    @ManyToOne
    @JoinColumn(name = "cycle_id")
    private CycleScolaire cycleScolaire;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "niveau", fetch = FetchType.LAZY)
    private List<Matiere> matieres;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "niveauScolaire", fetch = FetchType.LAZY)
//    private List<User> users;



}
