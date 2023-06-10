package Backend.service;


import Backend.Dto.LeconDto;
import Backend.entities.Lecon;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeconService {
    ResponseEntity<String> addNewLecon (LeconDto leconDto);
    ResponseEntity<List<LeconDto>> getAllLeconByChapitre(String chapitreId);

    ResponseEntity<String> updateLecon(LeconDto leconDto);
    ResponseEntity<String>deleteLecon(String leconId);
}
