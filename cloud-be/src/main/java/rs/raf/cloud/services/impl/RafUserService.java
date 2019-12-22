package rs.raf.cloud.services.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.raf.cloud.repository.UserRepository;
import rs.raf.cloud.services.UserService;

@Service
public class RafUserService implements UserService {

    @Autowired
    @Getter
    UserRepository userRepository;

}
