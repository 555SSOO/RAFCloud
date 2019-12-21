package rs.raf.chillflix.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import rs.raf.chillflix.controllers.constants.ControllerConstants;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = ControllerConstants.ANGULAR_URL)
@RequestMapping("/user")
public class UserController {

    @PostMapping(path = "/set-current-user", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void setCurrentUser(@RequestBody String userId, HttpServletRequest request) {
        request.getSession(true).setAttribute("currentUser", userId);
    }

}
