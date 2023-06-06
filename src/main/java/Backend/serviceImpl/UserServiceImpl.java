package Backend.serviceImpl;

import Backend.JWT.JwtFilter;
import Backend.JWT.JwtUtil;
import Backend.JWT.UsersDetailsService;
import Backend.constants.Constants;
import Backend.entities.User;
import Backend.repository.UserRepository;
import Backend.service.UserService;
import Backend.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
                    return UserUtils.getResponseEntity("Successfully registered", HttpStatus.OK);
                } else {
                    return UserUtils.getResponseEntity("Email already exist", HttpStatus.BAD_REQUEST);
                }
            } else {
                return UserUtils.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }


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
