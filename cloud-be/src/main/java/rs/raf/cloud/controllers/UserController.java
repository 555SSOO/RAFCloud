package rs.raf.cloud.controllers;

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
import rs.raf.cloud.controllers.constants.ControllerConstants;
import rs.raf.cloud.entities.User;
import rs.raf.cloud.services.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = ControllerConstants.ANGULAR_URL)
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Getter
    UserService customerService;
//
//    @GetMapping(value = "/get-all")
//    public List<User> getCustomers() {
//        return getCustomerService().getCustomers();
//    }

}
