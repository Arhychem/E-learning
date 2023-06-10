package Backend.service;

import Backend.Dto.SystemeDto;
import Backend.entities.Systeme;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Service
public interface SystemService {
    ResponseEntity<String> addNewSystem(Map<String, String> requestMap);

    ResponseEntity<Systeme> getSysteme();
    ResponseEntity<List<Systeme>> getAllSubSysteme();

    ResponseEntity<String> updateSysteme(Map<String, String> requestMap);

    ResponseEntity<String>deleteSysteme(Map<String, String> requestMap);
    ResponseEntity<List<SystemeDto>>getAllSousSystemeBySystemeParentId(String systemeParentId);
}
