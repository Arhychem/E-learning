package Backend.serviceImpl;

import Backend.Dto.CycleScolaireDto;
import Backend.Dto.MatiereDto;
import Backend.entities.Matiere;
import Backend.repository.MatiereRepository;
import Backend.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatiereServiceImpl implements MatiereService {
    @Autowired
    MatiereRepository matiereRepository;
    @Override
    public ResponseEntity<List<MatiereDto>> getAllMatiereByNiveau(String niveauId) {
        try {
            return new ResponseEntity<>(
                    matiereRepository.findAllMatiereByNiveau(niveauId)
                            .stream().map(MatiereDto::map).collect(Collectors.toList())
                    , HttpStatus.OK);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
