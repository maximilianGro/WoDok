package eHealth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import eHealth.config.properties.SecurityProperties;
import eHealth.dto.UserLoginDto;
import eHealth.exception.NotFoundException;
import eHealth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final AuthenticationManager authenticationManager;
    private final JwtTokenizer jwtTokenizer;
    private final UserService userService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, SecurityProperties securityProperties,
                                   JwtTokenizer jwtTokenizer, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenizer = jwtTokenizer;
        this.userService = userService;
        setFilterProcessesUrl(securityProperties.getLoginUri());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        UserLoginDto user = null;
        int counter = 0;
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), UserLoginDto.class);
            // Compares the user with CustomUserDetailService#loadUserByUsername and checks if the credentials are correct

            // Locked users can not log in
            counter = userService.findUserByEmail(user.getEmail()).getLockedCounter();
            if (5 <= counter) {
                throw new BadCredentialsException("The user is locked");
            } else {
                return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()));
            }
        } catch (NotFoundException e) {
            LOGGER.error("Unsuccessful authentication attempt for user {}", user.getEmail());
            throw new BadCredentialsException("No user found with the given e-mail address", e);
        } catch (IOException e) {
            LOGGER.error("Wrong API request or JSON schema for user {}", user.getEmail());
            throw new BadCredentialsException("Wrong API request or JSON schema", e);
        } catch (BadCredentialsException e) {
            if (counter < 5) {
                if (user != null && user.getEmail() != null) {
                    LOGGER.error("Unsuccessful authentication attempt for user {}", user.getEmail());
                    // Increase the lockedCounter of the user
                    userService.updateLockedCounter(user.getEmail());
                }
            } else {
                LOGGER.error("The user is locked");
                // LockedCounter will not be increased anymore
            }
            throw e;
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(failed.getMessage());
        LOGGER.debug("Invalid authentication attempt: {}", failed.getMessage());
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {
        User user = ((User) authResult.getPrincipal());

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        response.getWriter().write(jwtTokenizer.getAuthToken(user.getUsername(), roles));

        // Reset the lockedCounter of a user
        userService.resetLockedCounter(user.getUsername());
        LOGGER.info("Successfully authenticated user {}", user.getUsername());
    }
}
