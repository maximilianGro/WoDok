package eHealth.service.impl;

import eHealth.Repository.UserRepository;
import eHealth.entity.User;
import eHealth.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
public class UserService implements eHealth.service.UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOGGER.debug("Load all user by email");
        try {
            User user = findUserByEmail(email);

            List<GrantedAuthority> grantedAuthorities;
            if (user.getDoctor()) {
                grantedAuthorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
            } else {
                grantedAuthorities = AuthorityUtils.createAuthorityList("ROLE_USER");
            }

            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }
    }

    @Override
    public User findUserByEmail(String email) {
        LOGGER.debug("Find application user by email");
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new NotFoundException(String.format("Could not find the user with the email address %s", email));
        } else {
            return user;
        }
    }

    @Override
    public void updateLockedCounter(String email) {
        LOGGER.debug("Update the locker counter of the user");
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new NotFoundException("No user found with the given e-mail address");
        } else {
            user.setLockedCounter(user.getLockedCounter() + 1);
            userRepository.save(user);
        }
    }

    @Override
    public void resetLockedCounter(String email) {
        LOGGER.debug("Reset the locker counter of the user");
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new NotFoundException("No user found with the given e-mail address");
        } else {
            user.setLockedCounter(0);
            userRepository.save(user);
        }
    }
}
