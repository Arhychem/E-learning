package Backend.entities;


import Backend.utils.IdWithPrefixeGenerator;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = "User.findByEmailId", query = "select u from User u where u.email=:email")
@NamedQuery(name = "User.getAllUser", query = "select new Backend.wrapper.UserWrapper(u.id,u.name,u.email,u.contactNumber,u.status) from User u where u.role='apprenant'")
@NamedQuery(name = "User.updateStatus", query = "update User u set u.status=:status where u.id=:id")
@NamedQuery(name = "User.getAllAdmin", query = "select u.email from User u where u.role='enseignant'")

@Getter
@Setter
@NoArgsConstructor
//@RequiredArgsConstructor
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userId_seq")
    @GenericGenerator(name = "userId_seq",
        strategy = "Backend.utils.IdWithPrefixeGenerator",
    parameters = {
            @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.VALUE_PREFIX_PARAMETER, value = "user_"),
            @org.hibernate.annotations.Parameter(name = IdWithPrefixeGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
    })
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(columnDefinition = "varchar(255) default 'enseignant'")
    private String role = "enseignant";

    @Column(name = "contactNumber")
    private String contactNumber;

    @Column(columnDefinition = "varchar(255) default 'true'")
    private String status = "true";


//    @ManyToOne
//    @JoinColumn(name = "niveau_id")
//    private Niveau niveauScolaire;
}
