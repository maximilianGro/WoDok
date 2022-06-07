package eHealth.datagenerator;

import eHealth.Repository.UserRepository;
import eHealth.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;

@Profile("generateData")
@Component
public class UserDataGenerator implements DataGenerator{

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final int NUMBER_OF_USERS_TO_GENERATE = 10;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDataGenerator(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void generate() {
        if (userRepository.findAll().size() < 1) {
            LOGGER.debug("users already generated");
        } else {
            LOGGER.debug("generating {} user entries", NUMBER_OF_USERS_TO_GENERATE);
            User user = new User("email", passwordEncoder.encode("pw"), 5,
            false, "firstName",  "lastName",  "city",
                    "zip", "country", "street", "birthday");
            userRepository.save(user);

        }
    }
}
