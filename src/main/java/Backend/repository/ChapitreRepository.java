package Backend.repository;

import Backend.entities.Chapitre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapitreRepository extends JpaRepository<Chapitre, String> {
    @Query(
            value = " select c from Chapitre c " +
                    "LEFT JOIN FETCH c.matiere matiere " +
                    "where matiere.id = :matiereId")
    List<Chapitre> findAllChapitreByMatiereId(String matiereId);

}
