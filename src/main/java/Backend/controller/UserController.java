package Backend.controller;

import Backend.constants.Constants;
import Backend.service.UserService;
import Backend.utils.Utils;
import Backend.wrapper.UserWrapper;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody(required = true)Map<String, String> requestMap){
        try {
            return userService.login(requestMap);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping(path = "/changePassword")
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try {
           return userService.changePassword(requestMap);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/checkToken")
    public ResponseEntity<String> checkToken() {
        try {
            return userService.checkToken();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/getAllApprenant")
    public  ResponseEntity<List<UserWrapper>> getAllUser(){
        try {
            return userService.getAllUser();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/updatestatus")
    ResponseEntity<String> updateOwnProfile(@RequestBody Map<String, String> requestMap){
        try {
            return userService.updateOwnProfile(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/forgotPassword")
    ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> requestMap){
        try {
            return userService.forgotPassword(requestMap);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
