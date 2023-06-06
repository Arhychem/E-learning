package Backend.controller;

import Backend.constants.Constants;
import Backend.service.UserService;
import Backend.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RestController
@RequestMapping("/user/")


public class UserController {
    @Autowired
    UserService userService;
    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap){
        try {
            return userService.signUp(requestMap);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
