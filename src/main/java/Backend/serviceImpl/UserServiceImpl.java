package Backend.serviceImpl;

import Backend.JWT.JwtFilter;
import Backend.JWT.JwtUtil;
import Backend.JWT.UsersDetailsService;
import Backend.constants.Constants;
import Backend.entities.User;
import Backend.repository.UserRepository;
import Backend.service.UserService;
import Backend.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

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

    private boolean validateSignUpMap(Map<String, String> requestMap){
        if (requestMap.containsKey("name") &&
                requestMap.containsKey("email") && requestMap.containsKey("password") && requestMap.containsKey("role")
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

        user.setRole(requestMap.get("role"));
        return user;
    }
}
