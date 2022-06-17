package eHealth.service.impl;

import eHealth.Repository.UserRepository;
import eHealth.dto.UserRegisterDto;
import eHealth.entity.User;
import eHealth.exception.ContextException;
import eHealth.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
public class UserService implements eHealth.service.UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        LOGGER.debug("Find user by email");
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

    @Override
    public User createUser(UserRegisterDto user) {
        LOGGER.debug("Create application user");
        if (user == null) {
            throw new IllegalArgumentException("Please fill out all the mandatory fields");
        }
        User foundUser = userRepository.findUserByEmail(user.getEmail());
        if (foundUser != null) {
            throw new ContextException("E-mail already used");
        } else {
            return userRepository.save(new User(user.getEmail(), passwordEncoder.encode(user.getPassword()), 0,
                    false, user.getFirstName(), user.getLastName(), user.getCity(), user.getZip(), user.getCountry(), user.getStreet(),
                    user.getBirthday()));
        }
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new NotFoundException("Could not find user with id " + id);
        }
        return user;
    }

    @Override
    public Long getUserIdByEmail(String email) {
        return userRepository.findUserByEmail(email).getId();
    }
}
