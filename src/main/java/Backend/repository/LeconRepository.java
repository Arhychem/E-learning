package Backend.repository;

import Backend.entities.Chapitre;
import Backend.entities.Lecon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeconRepository extends JpaRepository<Lecon, String> {
    @Query(
            value = " select c from Lecon c " +
                    "LEFT JOIN FETCH c.chapitre chapitre " +
                    "where chapitre.id = :chapitreId")
    List<Lecon> findAllLeconByChapitreId(String chapitreId);
}
