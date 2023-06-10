package Backend.controller;

import Backend.Dto.ChapitreDto;
import Backend.entities.Chapitre;
import Backend.entities.Systeme;
import Backend.service.ChapitreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/chapitre/")
public class ChapitreController {
    @Autowired
    ChapitreService chapitreService;

    @PostMapping(path = "/add")
    ResponseEntity<String> addNewChapitre (@RequestBody  ChapitreDto chapitreDto){
        return chapitreService.addNewChapitre(chapitreDto);
    }
    @PostMapping(path = "/update")
    ResponseEntity<String> updateChapitre(@RequestBody ChapitreDto chapitreDto){
        return chapitreService.updateChapitre(chapitreDto);
    }
    @DeleteMapping(path = "/delete/{chapitreId}")
    ResponseEntity<String>deleteChapitre(@PathVariable  String chapitreId){
        return chapitreService.deleteChapitre(chapitreId);
    }

    @GetMapping(path = "/getAllByMatiereId")
    ResponseEntity<List<ChapitreDto>> getAllChapitreByMatiere(@RequestParam String matiereId){
        return chapitreService.getAllChapitreByMatiere(matiereId);
    }
}
