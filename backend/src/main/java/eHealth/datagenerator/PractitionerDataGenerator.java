package eHealth.datagenerator;

import eHealth.Repository.PractitionerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;

@Profile("generateData")
@Component
public class PractitionerDataGenerator implements DataGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final int NUMBER_OF_USERS_TO_GENERATE = 10;
    private final PractitionerRepository practitionerRepository;

    public PractitionerDataGenerator(PractitionerRepository practitionerRepository) {
        this.practitionerRepository = practitionerRepository;
    }
    @Override
    public void generate() {
        if (!practitionerRepository.findAll().isEmpty()) {
            LOGGER.debug("practitioners already generated");
        } else {
            LOGGER.debug("generating {} user entries", NUMBER_OF_USERS_TO_GENERATE);


        }
    }
}
