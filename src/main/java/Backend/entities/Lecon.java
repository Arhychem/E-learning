package Backend.entities;

import Backend.label.MatiereNameLabel;
import Backend.utils.IdWithPrefixeGenerator;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "lecon")
public class Lecon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lecon_seq")
    @GenericGenerator(name = "lecon_seq",
            strategy = "Backend.utils.IdWithPrefixeGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.VALUE_PREFIX_PARAMETER, value = "lecon_"),
                    @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    @Column(name = "leconId")
    private String leconId;

    @Column(name = "lecon_name")
    private String leconName;

    @ManyToOne
    @JoinColumn(name = "chapitre_id")
    private Chapitre chapitre;
}
