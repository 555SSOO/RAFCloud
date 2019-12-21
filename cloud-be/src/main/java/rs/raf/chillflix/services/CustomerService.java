package rs.raf.chillflix.services;

import rs.raf.chillflix.entities.Customer;
import rs.raf.chillflix.entities.User;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers();

    List<User> getUsersForCustomer(String username);

    Customer getCustomerByUsername(String username) throws Exception;

}
