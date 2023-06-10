package Backend.controller;

import Backend.Dto.SystemeDto;
import Backend.constants.Constants;
import Backend.entities.Systeme;
import Backend.service.SystemService;
import Backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/systemeeducatif")
public class SystemController {

    @Autowired
    SystemService systemService;
    @PostMapping(path = "/add")
    ResponseEntity<String> addNewSystem(@RequestBody (required = true)Map<String, String> requestMap){
        try {
            return systemService.addNewSystem(requestMap);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/getParentSysteme")
    ResponseEntity<Systeme> getSysteme(){
        try {
            return systemService.getSysteme();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Systeme(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/getAllSubSysteme")
    public ResponseEntity<List<Systeme>> getAllSubSysteme() {
        try {
            return systemService.getAllSubSysteme();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/getSousSytemeByParentId")
    public ResponseEntity<List<SystemeDto>> getAllSousSystemeBySystemeParentId(@RequestParam String systemeParentId){
        return systemService.getAllSousSystemeBySystemeParentId(systemeParentId);
    }

    @PostMapping(path = "/updateSysteme")
    public ResponseEntity<String> updateSysteme(@RequestBody  Map<String, String> requestMap){
        try {
            return systemService.updateSysteme(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(path = "/deleteSysteme")
    public ResponseEntity<String> deleteSysteme(@RequestBody  Map<String, String> requestMap) {
            return systemService.deleteSysteme(requestMap);
    }
    }

