package eHealth.datagenerator;

import eHealth.Repository.UserRepository;
import eHealth.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.invoke.MethodHandles;

@Profile("generateData")
@Component
public class UserDataGenerator implements DataGenerator{

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final int NUMBER_OF_USERS_TO_GENERATE = 10;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PractitionerDataGenerator practitionerDataGenerator;

    public UserDataGenerator(UserRepository userRepository, PasswordEncoder passwordEncoder, PractitionerDataGenerator practitionerDataGenerator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.practitionerDataGenerator = practitionerDataGenerator;
    }

    @Override
    @PostConstruct
    public void generate() {

//        if (userRepository.findAll().size() < 1) {
//            LOGGER.debug("users already generated");
//        } else {
//            LOGGER.debug("generating {} user entries", NUMBER_OF_USERS_TO_GENERATE);
//            for (int i = 0; i < NUMBER_OF_USERS_TO_GENERATE; i++) {
//                userRepository.save(new User("email", passwordEncoder.encode("password"), 5,
//                        false, "firstName" + NUMBER_OF_USERS_TO_GENERATE,  "lastName",  "city",
//                        "zip", "country", "street", "birthday"));
//            }
//
//        }
        if (userRepository.findAll().size() > 0) {
            LOGGER.debug("user already generated");
        } else {
            LOGGER.info("Generating users...");
//        userRepository.deleteAll();
//        userRepository.flush();
            userRepository.save(new User("k.arend@gmail.com", passwordEncoder.encode("password"), 0, false,
                    "Katrin", "Arend", "Ried Im Oberinntal", "6531", "Österreich", "Marktplatz 38", "05.09.1988"));
            userRepository.save(new User("e.soeren@gmail.com", passwordEncoder.encode("password"), 0, false,
                    "Elfriede", "Sören", "Winklern", "8953", "Österreich", "Neuhofer Strasse 48", "13.12.2002"));
            userRepository.save(new User("c.hedy@gmail.com", passwordEncoder.encode("password"), 0, false,
                    "Christa", "Hedy", "Salzburg", "5612", "Österreich", "Ditscheinergasse 70", "27.01.187"));
            this.practitionerDataGenerator.generate();
        }
    }
}
