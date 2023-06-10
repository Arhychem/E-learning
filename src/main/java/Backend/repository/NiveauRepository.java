package Backend.repository;

import Backend.entities.CycleScolaire;
import Backend.entities.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NiveauRepository extends JpaRepository<Niveau, String> {
    @Query(
            value = " select c from Niveau c " +
                    "LEFT JOIN FETCH c.cycleScolaire cycleScolaire " +
                    "where cycleScolaire.id = :cycleId")
    List<Niveau> findAllNiveauByCycle(String cycleId);
}
