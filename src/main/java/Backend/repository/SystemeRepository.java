package Backend.repository;

import Backend.entities.Systeme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemeRepository extends JpaRepository<Systeme, String> {
    List<Systeme> getAllSubSysteme();
    Systeme getSysteme();


    Systeme findByName(@Param("systeme_name") String systemeName); //On reporte le nom du param dans la requete

    @Query(
    value = " select systeme from Systeme systeme " +
            "LEFT JOIN FETCH systeme.systemeEducatifParent systemeEducatifParent " +
            "where systemeEducatifParent.id = :systemeParentId")
    List<Systeme> findAllSousSystemeBySystemeParentId(String systemeParentId);
}
