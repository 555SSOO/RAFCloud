package rs.raf.chillflix.services.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.raf.chillflix.entities.User;
import rs.raf.chillflix.repository.UserRepository;
import rs.raf.chillflix.services.UserService;

@Service
public class CfUserService implements UserService {

    @Autowired
    @Getter
    UserRepository userRepository;

    @Override
    public User getUserFromId(String id) throws Exception {
        return getUserRepository().findById(Long.valueOf(id)).orElseThrow(() -> new Exception("Couldn't find a User with id: " + id));
    }

}
