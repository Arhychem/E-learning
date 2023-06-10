package Backend.entities;

import Backend.utils.IdWithPrefixeGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@NamedQuery(name = "Systeme.getAllSubSysteme", query = "select s from Systeme s where s.systemeEducatifParent is not null")
//@NamedQuery(name = "Systeme.findAllSousSystemeBySystemeParentId", query = "select s from Systeme s where s.systemeEducatifParent=:systeme_parent_id")
@NamedQuery(name = "Systeme.getSysteme", query = "select s from Systeme s where s.systemeEducatifParent is null")
@NamedQuery(name = "Systeme.findByName", query = "select s from Systeme s where s.systemeName=:systeme_name")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "systemeEducatif")
public class Systeme {
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
    private String systemeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "systeme_parent_id")
    private Systeme systemeEducatifParent;

    @OneToMany(mappedBy = "systemeEducatifParent")
    private List<Systeme> sousSystemesEducatifs; // liaison reccursive de systeme dans lui-meme car un sous syteme est un systeme

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sousSystemeEducatif", fetch = FetchType.LAZY)
    private List<CycleScolaire> cycles;
 }
