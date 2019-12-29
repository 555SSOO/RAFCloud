package rs.raf.cloud.services.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.raf.cloud.entities.User;
import rs.raf.cloud.repository.UserRepository;
import rs.raf.cloud.services.UserService;

import java.util.Objects;

@Service
public class RafUserService implements UserService {

    @Autowired
    @Getter
    UserRepository userRepository;

    @Override
    public boolean validateToken(String token, String username) {
        User user = getUserRepository().findByUsername(username);
        if(Objects.nonNull(user.getCurrentToken())) {
            return token.equals(user.getCurrentToken());
        } else return false;
    }
}
