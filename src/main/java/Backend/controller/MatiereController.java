package Backend.controller;

import Backend.Dto.MatiereDto;
import Backend.entities.Matiere;
import Backend.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/matiere/")
public class MatiereController {
    @Autowired
    MatiereService matiereService;

    @GetMapping("/getAllByNiveauId")
    ResponseEntity<List<MatiereDto>> getAllMatiereByNiveau(@RequestParam String niveauId){
        return matiereService.getAllMatiereByNiveau(niveauId);
    }

}
