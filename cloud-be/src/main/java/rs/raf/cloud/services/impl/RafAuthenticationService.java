package rs.raf.cloud.services.impl;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.raf.cloud.entities.User;
import rs.raf.cloud.services.AuthenticationService;
import rs.raf.cloud.services.UserService;

@Service
public class RafAuthenticationService implements AuthenticationService {

    private static final Logger LOG = LoggerFactory.getLogger(RafAuthenticationService.class);

    @Autowired
    @Getter
    UserService customerService;

    @Override
    public boolean isPasswordCorrect(String username, String password){
//        try {
//            User customer = getCustomerService().getCustomerByUsername(username);
//            return customer.getPassword().equals(password);
//        } catch (Exception e) {
//            LOG.info("AuthService exception: " + e);
//            return false;
//        }
        return true;
    }

}
