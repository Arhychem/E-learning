package Backend.serviceImpl;

import Backend.Dto.SystemeDto;
import Backend.JWT.JwtFilter;
import Backend.JWT.JwtUtil;
import Backend.constants.Constants;
import Backend.entities.Systeme;
import Backend.repository.SystemeRepository;
import Backend.service.SystemService;
import Backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    SystemeRepository systemeRepository;

    @Override
    public ResponseEntity<String> addNewSystem(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateSystemMap(requestMap) && requestMap.containsKey("systemeParentId")) {
                    Systeme systeme = systemeRepository.findByName(requestMap.get("systemeName"));

                    if (!Objects.isNull(systeme)) {
                        return Utils.getResponseEntity("System already exist", HttpStatus.BAD_REQUEST);
                    } else {

                        systemeRepository.save(getSystemFromMap(requestMap));
                        return Utils.getResponseEntity("Successfully added", HttpStatus.OK);
                    }
                }
            } else {
                return Utils.getResponseEntity(Constants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Systeme> getSysteme() {
        try {
            return new ResponseEntity<>(systemeRepository.getSysteme(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Systeme(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Systeme>> getAllSubSysteme() {
        try {
            return new ResponseEntity<>(systemeRepository.getAllSubSysteme(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateSysteme(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (requestMap.containsKey("systemeName") && requestMap.containsKey("systemeId")) {
                    Optional<Systeme> optional = systemeRepository.findById(requestMap.get("systemeId"));
                    if (!optional.isEmpty()) {
                        Systeme systeme = new Systeme();
                        systeme.setSystemeId(requestMap.get("systemeId"));
                        systeme.setSystemeName(requestMap.get("systemeName"));
                        if (systemeRepository.findByName(systeme.getSystemeName()) != null) {
                            return Utils.getResponseEntity("System already exist with this name", HttpStatus.BAD_REQUEST);
                        } else {
                            systemeRepository.save(systeme);
                            return Utils.getResponseEntity("Successfully updated", HttpStatus.OK);
                        }

                    } else {
                        return Utils.getResponseEntity("There is no systeme with this name", HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return Utils.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }
            } else {
                return Utils.getResponseEntity(Constants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteSysteme(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (requestMap.containsKey("systemeId")) {
                    Optional<Systeme> optionalSysteme = systemeRepository.findById(requestMap.get("systemeId"));
                    if (optionalSysteme.isPresent()) {
                        systemeRepository.delete(optionalSysteme.get());
                        return Utils.getResponseEntity("Successfully deleted", HttpStatus.OK);
                    }
                } else {
                    return Utils.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }
            } else {
                return Utils.getResponseEntity(Constants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<SystemeDto>> getAllSousSystemeBySystemeParentId(String systemeParentId) {
        try {
            List<SystemeDto> systemeList = systemeRepository.findAllSousSystemeBySystemeParentId(systemeParentId)
                    .stream().map(SystemeDto::map).collect(Collectors.toList());
            return new ResponseEntity<>(systemeList, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSystemMap(Map<String, String> requestMap) {
        if (requestMap.containsKey("systemeName")) {
            return true;
        }
        return false;
    }

    private Systeme getSystemFromMap(Map<String, String> requestMap) {
        Systeme systeme = new Systeme();
        try {
            if (requestMap.containsKey("systemeParentId")) {
                if (Objects.isNull(requestMap.get("systemeParentId"))
                        || requestMap.get("systemeParentId").equals("")) {

                } else {
                    Optional<Systeme> systemeParent = systemeRepository.findById(requestMap.get("systemeParentId"));
                    if (systemeParent.isEmpty()) {

                    } else {
                        systeme.setSystemeEducatifParent(systemeParent.get());
                    }
                }
            }
            systeme.setSystemeName(requestMap.get("systemeName"));
            return systeme;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return systeme;
    }
}
