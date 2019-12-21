package rs.raf.chillflix.services.impl;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.raf.chillflix.entities.Customer;
import rs.raf.chillflix.services.AuthenticationService;
import rs.raf.chillflix.services.CustomerService;

@Service
public class CfAuthenticationService implements AuthenticationService {

    private static final Logger LOG = LoggerFactory.getLogger(CfAuthenticationService.class);

    @Autowired
    @Getter
    CustomerService customerService;

    @Override
    public boolean isPasswordCorrect(String username, String password){
        try {
            Customer customer = getCustomerService().getCustomerByUsername(username);
            return customer.getPassword().equals(password);
        } catch (Exception e) {
            LOG.info("AuthService exception: " + e);
            return false;
        }
    }

}
