package Backend.service;

import Backend.Dto.MatiereDto;
import Backend.entities.Matiere;
import Backend.entities.Niveau;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface MatiereService {
    ResponseEntity<List<MatiereDto>> getAllMatiereByNiveau(String niveauId); //Penser à utiliser les dto et non pas les entités
}
