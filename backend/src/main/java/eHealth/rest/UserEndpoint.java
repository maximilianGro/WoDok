package eHealth.rest;

import eHealth.dto.UserLoginDto;
import eHealth.dto.UserRegisterDto;
import eHealth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
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
        /*if (bindingResult.hasErrors()) {
            ObjectError e = bindingResult.getAllErrors().get(0);
            LOGGER.error(e.getDefaultMessage(), e);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Validation failed: " + e.getDefaultMessage());
        }*/
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
