package Backend.controller;

import Backend.Dto.ChapitreDto;
import Backend.Dto.LeconDto;
import Backend.entities.Chapitre;
import Backend.entities.Lecon;
import Backend.service.LeconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/lecon/")
public class LeconController {
    @Autowired
    LeconService leconService;

    @PostMapping(path = "/add")
    ResponseEntity<String> addNewLecon (@RequestBody LeconDto leconDto){
        return leconService.addNewLecon(leconDto);
    }
    @PostMapping(path = "/update")
    ResponseEntity<String> updateLecon(@RequestBody LeconDto chapitreDto){
        return leconService.updateLecon(chapitreDto);
    }
    @DeleteMapping(path = "/delete")
    ResponseEntity<String>deleteLecon(@PathVariable  String chapitreId){
        return leconService.deleteLecon(chapitreId);
    }

    @GetMapping(path = "/getAllByChapitreId")
    ResponseEntity<List<LeconDto>> getAllLeconByMatiere(@RequestParam String chapitreId){
        return leconService.getAllLeconByChapitre(chapitreId);
    }
}
