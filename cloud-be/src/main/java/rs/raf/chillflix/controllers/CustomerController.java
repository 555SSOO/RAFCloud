package rs.raf.chillflix.controllers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rs.raf.chillflix.controllers.constants.ControllerConstants;
import rs.raf.chillflix.entities.Customer;
import rs.raf.chillflix.entities.User;
import rs.raf.chillflix.services.CustomerService;

import java.util.List;

@RestController
@CrossOrigin(origins = ControllerConstants.ANGULAR_URL)
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    @Getter
    CustomerService customerService;

    @GetMapping(value = "/get-all")
    public List<Customer> getCustomers() {
        return getCustomerService().getCustomers();
    }

    @RequestMapping(value = "/get-users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<User> getUsersForCustomer(@RequestParam String customerUsername) {
        return getCustomerService().getUsersForCustomer(customerUsername);
    }

}
