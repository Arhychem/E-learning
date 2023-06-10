package Backend.service;

import Backend.Dto.NiveauDto;
import Backend.entities.Niveau;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NiveauService {
    ResponseEntity<List<NiveauDto>> getAllNiveauByCycleId(String cycleId);
}
