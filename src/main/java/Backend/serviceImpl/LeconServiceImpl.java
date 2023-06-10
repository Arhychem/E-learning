package Backend.serviceImpl;

import Backend.Dto.ChapitreDto;
import Backend.Dto.LeconDto;
import Backend.JWT.JwtFilter;
import Backend.constants.Constants;
import Backend.entities.Chapitre;
import Backend.entities.Lecon;
import Backend.entities.Matiere;
import Backend.repository.ChapitreRepository;
import Backend.repository.LeconRepository;
import Backend.service.LeconService;
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
public class LeconServiceImpl implements LeconService {
    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    LeconRepository leconRepository;
    @Autowired
    ChapitreRepository chapitreRepository;

    @Override
    public ResponseEntity<String> addNewLecon(LeconDto leconDto) {
        try {
            if (jwtFilter.isAdmin()){
                Optional<Chapitre> optionaChapitre = chapitreRepository.findById(leconDto.getChapitreId());
                if (optionaChapitre.isEmpty()){
                    return Utils.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }
                Lecon lecon = Lecon.builder()
                        .leconName(leconDto.getLeconName())
                        .chapitre(optionaChapitre.get())
                        .build();
                leconRepository.save(lecon);
                return Utils.getResponseEntity("Successfully added", HttpStatus.OK);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<LeconDto>> getAllLeconByChapitre(String chapitreId) {
        try {
            return new ResponseEntity<>(
                    leconRepository.findAllLeconByChapitreId(chapitreId)
                            .stream().map(LeconDto::map).collect(Collectors.toList())
                    , HttpStatus.OK);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateLecon(LeconDto leconDto) {
        try {
            if (jwtFilter.isAdmin()){
                Optional<Lecon> optional = leconRepository.findById(leconDto.getLeconId());
                if (optional.isEmpty()){
                    return Utils.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }
                else {
                    optional.get().setLeconName(leconDto.getLeconName());
                    leconRepository.save(optional.get());
                    return Utils.getResponseEntity("Successfully updated", HttpStatus.OK);
                }
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteLecon(String leconId) {
        try{
            if (jwtFilter.isAdmin()){
                Optional<Lecon> optionalLecon = leconRepository.findById(leconId);
                if (optionalLecon.isPresent()) {
                    leconRepository.delete(optionalLecon.get());
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
