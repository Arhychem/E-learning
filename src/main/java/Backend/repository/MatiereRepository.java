package Backend.repository;

import Backend.entities.Matiere;
import Backend.entities.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MatiereRepository extends JpaRepository<Matiere, String> {
    @Query(
            value = " select c from Matiere c " +
                    "LEFT JOIN FETCH c.niveau niveau " +
                    "where niveau.id = :niveauId")
    List<Matiere> findAllMatiereByNiveau(String niveauId);
}
