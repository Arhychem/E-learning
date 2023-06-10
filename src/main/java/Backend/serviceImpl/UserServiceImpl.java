package Backend.serviceImpl;


import Backend.JWT.JwtFilter;
import Backend.JWT.JwtUtil;
import Backend.JWT.UsersDetailsService;
import Backend.constants.Constants;
import Backend.entities.User;
import Backend.repository.UserRepository;
import Backend.service.UserService;
import Backend.utils.EmailUtils;
import Backend.utils.Utils;
import Backend.wrapper.UserWrapper;
import com.google.common.base.Strings;
import jdk.jshell.execution.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersDetailsService customerUsersDetailsService;

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    EmailUtils emailUtils;


    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userRepository.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userRepository.save(getUserFromMap(requestMap));
                    return Utils.getResponseEntity("Successfully registered", HttpStatus.OK);
                } else {
                    return Utils.getResponseEntity("Email already exist", HttpStatus.BAD_REQUEST);
                }
            } else {
                return Utils.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @Override
    public ResponseEntity<String> checkToken() {
        return Utils.getResponseEntity("true", HttpStatus.OK);
    }
    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login");
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
            );
            if (auth.isAuthenticated()){
                if (customerUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")){
                    return new ResponseEntity<String>("{\"token\":\""+
                            jwtUtil.generateToken(customerUsersDetailsService.getUserDetail().getEmail(),
                                    customerUsersDetailsService.getUserDetail().getRole())+"\"}",
                            HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<String>("{\"message\":\""+"Wait for admin approval."+"\"}",
                            HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return new ResponseEntity<String>("{\"message\":\""+"Incorrect username or password"+"\"}",
                        HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("{}", e);
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try{
            User user = userRepository.findByEmailId(jwtFilter.getCurrentUser());
            if (!user.equals(null)){
                if (user.getPassword().equals(requestMap.get("oldPassword"))){
                    user.setPassword(requestMap.get("newPassword"));
                    userRepository.save(user);
                    return Utils.getResponseEntity("Password updated successfully", HttpStatus.OK);
                }
                return Utils.getResponseEntity("Incorrect Old Password", HttpStatus.INTERNAL_SERVER_ERROR);

            }
            return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        try {
            if (jwtFilter.isAdmin()){
                return new ResponseEntity<>(userRepository.getAllUser(), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateOwnProfile(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()){
                Optional<User> optional = userRepository.findById(requestMap.get("id"));
                if (!optional.isEmpty()){
                    userRepository.updateStatus(requestMap.get("status"), requestMap.get("id"));
                    sendMailToAllAdmin(requestMap.get("status"), optional.get().getEmail(), userRepository.getAllAdmin());
                    return Utils.getResponseEntity("User status updated successfully", HttpStatus.OK);
                }
                else {
                    Utils.getResponseEntity("This id does' nt exist", HttpStatus.OK);
                }
            }else {
                return Utils.getResponseEntity("Unauthorized access", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        try{
            User user = userRepository.findByEmailId(requestMap.get("email"));
            if (!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail())){
                emailUtils.forgotMail(user.getEmail(), "Credential by eLearning_GI2023", user.getPassword());
            }
            return Utils.getResponseEntity("Check your mail for credentials.", HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {
        allAdmin.remove(jwtFilter.getCurrentUser());
        if (status != null && status.equalsIgnoreCase("true")){
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account approved", "USER:- "+user+"\n is approved by \nADMIN:-"+jwtFilter.getCurrentUser()+ ")",allAdmin);
        } else {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account approved", "USER:- "+user+"\n is disabled by \nADMIN:-"+jwtFilter.getCurrentUser()+ ")",allAdmin);
        }
    }


    private boolean validateSignUpMap(Map<String, String> requestMap){
        if (requestMap.containsKey("name") &&
                requestMap.containsKey("email") && requestMap.containsKey("password") && requestMap.containsKey("contactNumber")
                ){
            return true;
        }
        return false;
    }

    private User getUserFromMap(Map<String, String> requestMap){
        User user = new User();
        user.setName(requestMap.get("name"));

        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));

        user.setContactNumber(requestMap.get("contactNumber"));
        return user;
    }
}
