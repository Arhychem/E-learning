package Backend.service;

import Backend.Dto.CycleScolaireDto;
import Backend.entities.CycleScolaire;
import Backend.entities.Systeme;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CycleScolaireService {
    ResponseEntity<String> addNewCycle(CycleScolaireDto cycleScolaireDto);
    ResponseEntity<List<CycleScolaireDto>>getAllCycleBySousSystemeId(String sousSystemeId);

}
