package Backend.controller;

import Backend.Dto.CycleScolaireDto;
import Backend.entities.CycleScolaire;
import Backend.service.CycleScolaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/cycle/")
public class CycleScolaireController {
    @Autowired
    CycleScolaireService cycleScolaireService;
    @GetMapping("/getAllBySousSystemeId")
    public ResponseEntity<List<CycleScolaireDto>> getAllCycleBySousSystemeId(@RequestParam String sousSystemeId){
        return cycleScolaireService.getAllCycleBySousSystemeId(sousSystemeId);
    }
}
