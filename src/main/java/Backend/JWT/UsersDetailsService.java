package Backend.JWT;

import Backend.entities.User;
import Backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service

public class UsersDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    private User userDetail;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Inside loadByUsername {}", email);
        userDetail = userRepository.findByEmailId(email);
        if (!Objects.isNull(userDetail)){
            return new org.springframework.security.core.userdetails.User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public User getUserDetail(){
        return userDetail;
    }
}
