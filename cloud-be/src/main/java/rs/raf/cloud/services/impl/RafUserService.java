package rs.raf.cloud.services.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.raf.cloud.entities.User;
import rs.raf.cloud.repository.UserRepository;
import rs.raf.cloud.services.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class RafUserService implements UserService {

    @Autowired
    @Getter
    UserRepository userRepository;

//    @Override
//    public List<User> getUsers() {
//        return (List<User>) customerRepository.findAll();
//    }
//
//    @Override
//    public List<User> getUsersForCustomer(String username) {
//        return getCustomerRepository().findByUsername(username).getUsers();
//    }
//
//    @Override
//    public User getCustomerByUsername(String username) throws Exception {
//        Optional<User> optionalCustomer = Optional.ofNullable(getCustomerRepository().findByUsername(username));
//        return optionalCustomer.orElseThrow(() -> new Exception("Couldn't find a Customer with username: " + username));
//    }

}
