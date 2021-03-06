package rs.raf.cloud.controllers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rs.raf.cloud.controllers.constants.ControllerConstants;
import rs.raf.cloud.services.AuthenticationService;

@RestController
@CrossOrigin(origins = ControllerConstants.ANGULAR_URL)
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    @Getter
    AuthenticationService authenticationService;

    @RequestMapping(produces="text/plain")
    @ResponseBody
    public String authenticateUser(@RequestParam String username, @RequestParam String password) {
        return "\""+getAuthenticationService().isPasswordCorrect(username, password)+"\"";
    }

}
