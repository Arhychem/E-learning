package Backend.service;

import Backend.Dto.ChapitreDto;
import Backend.entities.Chapitre;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ChapitreService {
    ResponseEntity<String> addNewChapitre (ChapitreDto chapitreDto);
    ResponseEntity<List<ChapitreDto>> getAllChapitreByMatiere(String matiereId);

    ResponseEntity<String> updateChapitre(ChapitreDto chapitreDto);
    ResponseEntity<String>deleteChapitre(String chapitreString);
}
