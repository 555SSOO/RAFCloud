package rs.raf.cloud.services.impl;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.raf.cloud.entities.User;
import rs.raf.cloud.repository.UserRepository;
import rs.raf.cloud.services.AuthenticationService;
import rs.raf.cloud.services.UserService;

import java.util.UUID;

@Service
public class RafAuthenticationService implements AuthenticationService {

    private static final Logger LOG = LoggerFactory.getLogger(RafAuthenticationService.class);

    @Autowired
    @Getter
    UserService userService;

    @Autowired
    @Getter
    UserRepository userRepository;

    @Override
    public String isPasswordCorrect(String username, String password) {
        try {
            User user = getUserRepository().findByUsername(username);
            if (user.getPassword().equals(password)) {
                String token = UUID.randomUUID().toString();
                user.setCurrentToken(token);
                getUserRepository().save(user);
                return token;
            } else {
                return null;
            }
        } catch (Exception e) {
            LOG.info("AuthService exception: " + e);
            return null;
        }
    }

}
