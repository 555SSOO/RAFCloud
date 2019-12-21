package rs.raf.chillflix.services.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.raf.chillflix.entities.Customer;
import rs.raf.chillflix.entities.User;
import rs.raf.chillflix.repository.CustomerRepository;
import rs.raf.chillflix.services.CustomerService;

import java.util.List;
import java.util.Optional;

@Service
public class CfCustomerService implements CustomerService {

    @Autowired
    @Getter
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public List<User> getUsersForCustomer(String username) {
        return getCustomerRepository().findByUsername(username).getUsers();
    }

    @Override
    public Customer getCustomerByUsername(String username) throws Exception {
        Optional<Customer> optionalCustomer = Optional.ofNullable(getCustomerRepository().findByUsername(username));
        return optionalCustomer.orElseThrow(() -> new Exception("Couldn't find a Customer with username: " + username));
    }

}
