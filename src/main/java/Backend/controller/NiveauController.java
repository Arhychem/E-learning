package Backend.controller;

import Backend.Dto.NiveauDto;
import Backend.entities.Niveau;
import Backend.service.NiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/niveau/")
public class NiveauController {
    @Autowired
    NiveauService niveauService;

    @GetMapping("/getAllByCycleId")
    ResponseEntity<List<NiveauDto>> getAllNiveauByCycleId(@RequestParam String cycleId){
        return niveauService.getAllNiveauByCycleId(cycleId);
    }
}
