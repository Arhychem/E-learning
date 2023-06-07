package Backend.entities;

import Backend.label.SysNameLabel;
import Backend.utils.IdWithPrefixeGenerator;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "systemeEducatif")
public class System {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sysId_seq")
    @GenericGenerator(name = "sysId_seq",
            strategy = "Backend.utils.IdWithPrefixeGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.VALUE_PREFIX_PARAMETER, value = "sys_"),
                    @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.NUMBER_FORMAT_PARAMETER, value = "%02d")
            })
    @Column(name = "systemeId")
    private String systemeId;

    @Column(columnDefinition = "varchar(255)")
    private SysNameLabel systemeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "systeme_parent_id")
    private System systemeEducatif;

    @OneToMany(mappedBy = "systemeEducatif")
    private List<System> sousSystemesEducatifs; // liaison reccursive de systeme dans lui-meme car un sous syteme est un systeme

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sousSystemeEducatif", fetch = FetchType.LAZY)
//    private List<Cycle> cycles;
 }
