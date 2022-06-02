package eHealth.rest;

import eHealth.dto.UserLoginDto;
import eHealth.dto.UserRegisterDto;
import eHealth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping(UserEndpoint.BASE_URL)
public class UserEndpoint {
    private final UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final String BASE_URL = "/users";

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    /**
     * The registration route for the user.
     */
    @PermitAll
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody UserRegisterDto user) {
        LOGGER.info("POST " + BASE_URL + " " + user.toString());
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
