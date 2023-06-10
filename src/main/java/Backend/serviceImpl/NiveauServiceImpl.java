package Backend.serviceImpl;

import Backend.Dto.CycleScolaireDto;
import Backend.Dto.NiveauDto;
import Backend.entities.Niveau;
import Backend.repository.NiveauRepository;
import Backend.service.NiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NiveauServiceImpl implements NiveauService {
    @Autowired
    NiveauRepository niveauRepository;
    @Override
    public ResponseEntity<List<NiveauDto>> getAllNiveauByCycleId(String cycleId) {
        try {
            return new ResponseEntity<>(
                    niveauRepository.findAllNiveauByCycle(cycleId)
                            .stream().map(NiveauDto::map).collect(Collectors.toList())
                    , HttpStatus.OK);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
