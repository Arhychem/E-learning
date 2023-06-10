package Backend.serviceImpl;

import Backend.Dto.ChapitreDto;
import Backend.Dto.MatiereDto;
import Backend.JWT.JwtFilter;
import Backend.constants.Constants;
import Backend.entities.Chapitre;
import Backend.entities.CycleScolaire;
import Backend.entities.Matiere;
import Backend.entities.Systeme;
import Backend.repository.ChapitreRepository;
import Backend.repository.MatiereRepository;
import Backend.service.ChapitreService;
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
public class ChapitreServiceImpl implements ChapitreService {
    @Autowired
    ChapitreRepository chapitreRepository;
    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    MatiereRepository matiereRepository;


    @Override
    public ResponseEntity<String> addNewChapitre(ChapitreDto chapitreDto) {
        try {
            if (jwtFilter.isAdmin()){
                Optional<Matiere> optionalMatiere = matiereRepository.findById(chapitreDto.getMatiereId());
                if (optionalMatiere.isEmpty()){
                    return Utils.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }
                Chapitre chapitre = Chapitre.builder()
                        .chapitreName(chapitreDto.getChapitreName())
                        .matiere(optionalMatiere.get())
                        .build();
                chapitreRepository.save(chapitre);
                return Utils.getResponseEntity("Successfully added", HttpStatus.OK);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<ChapitreDto>> getAllChapitreByMatiere(String matiereId) {
        try {
            return new ResponseEntity<>(
                    chapitreRepository.findAllChapitreByMatiereId(matiereId)
                            .stream().map(ChapitreDto::map).collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateChapitre(ChapitreDto chapitreDto) {
        try {
            if (jwtFilter.isAdmin()){
                Optional<Chapitre> optional = chapitreRepository.findById(chapitreDto.getChapitreId());
                if (optional.isEmpty()){
                    return Utils.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }
                else {
                    optional.get().setChapitreName(chapitreDto.getChapitreName());
                    chapitreRepository.save(optional.get());
                    return Utils.getResponseEntity("Successfully updated", HttpStatus.OK);
                }
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteChapitre(String chapitreId) {
        try{
            if (jwtFilter.isAdmin()){
                    Optional<Chapitre> optionalChapitre = chapitreRepository.findById(chapitreId);
                    if (optionalChapitre.isPresent()) {
                        chapitreRepository.delete(optionalChapitre.get());
                        return Utils.getResponseEntity("Successfully deleted", HttpStatus.OK);
                    }
                    else {
                    return Utils.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return Utils.getResponseEntity(Constants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
    }
}
