package rs.raf.chillflix.controllers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.raf.chillflix.controllers.constants.ControllerConstants;
import rs.raf.chillflix.services.AuthenticationService;

@RestController
@CrossOrigin(origins = ControllerConstants.ANGULAR_URL)
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    @Getter
    AuthenticationService authenticationService;

    @GetMapping
    public boolean authenticateUser(@RequestParam String username, @RequestParam String password) {
        return getAuthenticationService().isPasswordCorrect(username, password);
    }

}
