package Backend.serviceImpl;

import Backend.Dto.CycleScolaireDto;
import Backend.Dto.SystemeDto;
import Backend.JWT.JwtFilter;
import Backend.JWT.JwtUtil;
import Backend.constants.Constants;
import Backend.entities.CycleScolaire;
import Backend.entities.Niveau;
import Backend.entities.Systeme;
import Backend.repository.CycleScolaireRepository;
import Backend.repository.SystemeRepository;
import Backend.service.CycleScolaireService;
import Backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CycleScolaireServiceImpl implements CycleScolaireService {
    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    CycleScolaireRepository cycleScolaireRepository;
    @Autowired
    SystemeRepository systemeRepository;
    @Override
    public ResponseEntity<String> addNewCycle(CycleScolaireDto cycleScolaireDto) {
        try {
            if (jwtFilter.isAdmin()){
                Optional<Systeme> optionalSystem = systemeRepository.findById(cycleScolaireDto.getSousSystemeEducatifId());
                if(optionalSystem.isEmpty()){
                    return Utils.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }
                CycleScolaire cycleScolaire = new CycleScolaire();
                cycleScolaire.setCycleScolaireName(cycleScolaireDto.getCycleScolaireName());
                cycleScolaire.setSousSystemeEducatif(optionalSystem.get());
                cycleScolaireRepository.save(cycleScolaire);
                return Utils.getResponseEntity("Successfully added", HttpStatus.OK);

            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<CycleScolaireDto>> getAllCycleBySousSystemeId(String sousSystemeId) {
        try {
            return new ResponseEntity<>(
                    cycleScolaireRepository.findAllCycleBySousSystemeId(sousSystemeId)
                            .stream().map(CycleScolaireDto::map).collect(Collectors.toList())
                    , HttpStatus.OK);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
