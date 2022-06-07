package eHealth.datagenerator;

import eHealth.Repository.PractitionerRepository;
import eHealth.entity.Practitioner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;

@Profile("generateData")
@Component
public class PractitionerDataGenerator implements DataGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final int NUMBER_OF_TO_GENERATE = 10;
    private final PractitionerRepository practitionerRepository;

    public PractitionerDataGenerator(PractitionerRepository practitionerRepository) {
        this.practitionerRepository = practitionerRepository;
    }
    @Override
    public void generate() {
        if (!practitionerRepository.findAll().isEmpty()) {
            LOGGER.debug("practitioners already generated");
        } else {
            LOGGER.debug("generating {} practitioners entries", NUMBER_OF_TO_GENERATE);
            for (int i = 0; i < NUMBER_OF_TO_GENERATE; i++) {
                practitionerRepository.save(new Practitioner("name" +NUMBER_OF_TO_GENERATE,
                        "nn", "fach"+NUMBER_OF_TO_GENERATE,
                        "9:00-10:00", "address", "0660", "email"));
            }


        }
    }
}
