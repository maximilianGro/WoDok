package eHealth.service;

import eHealth.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    /**
     * Find an application user based on the email address.
     *
     * @param email the email address
     * @return the application user with the given address
     */
    User findUserByEmail(String email);

    /**
     * Increase the lockedCounter of a user.
     *
     * @param email of the user
     */
    void updateLockedCounter(String email);

    /**
     * Reset the lockedCounter of a user.
     *
     * @param email of the user
     */
    void resetLockedCounter(String email);
}
