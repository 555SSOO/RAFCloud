package rs.raf.cloud.controllers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.raf.cloud.controllers.constants.ControllerConstants;
import rs.raf.cloud.services.UserService;

@RestController
@CrossOrigin(origins = ControllerConstants.ANGULAR_URL)
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Getter
    UserService customerService;
}
