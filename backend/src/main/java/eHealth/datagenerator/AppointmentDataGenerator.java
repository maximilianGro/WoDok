package eHealth.datagenerator;

import eHealth.Repository.AppointmentRepository;
import eHealth.Repository.PractitionerRepository;
import eHealth.Repository.UserRepository;
import eHealth.entity.Appointment;
import eHealth.entity.Practitioner;
import eHealth.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.List;

//@Profile("generateData")
@Component
public class AppointmentDataGenerator  implements DataGenerator {


    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final int NUMBER_TO_GENERATE = 10;
    private final AppointmentRepository appointmentRepository;
    private final PractitionerRepository practitionerRepository;
    private final UserRepository userRepository;


    public AppointmentDataGenerator(AppointmentRepository appointmentRepository, PractitionerRepository practitionerRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.practitionerRepository = practitionerRepository;
        this.userRepository = userRepository;
    }

    @Override
    @PostConstruct
    public void generate() {
        if (appointmentRepository.findAll().size() > 0) {
            LOGGER.debug("artists already generated");
        } else {
            LOGGER.debug("Generating practitioners...");
            appointmentRepository.deleteAll();
            appointmentRepository.flush();
            List<Practitioner> practitioners = practitionerRepository.findAll();
            List<User> users = userRepository.findAll();
            //free appointments
            for (int i = 0; i < practitioners.size(); i++) {
                appointmentRepository.save(new Appointment(true, practitioners.get(i), null, LocalDateTime.of(2022, 6, 21, 10, 0),
                        LocalDateTime.of(2022, 6, 21, 10, 45), null));
                appointmentRepository.save(new Appointment(true, practitioners.get(i), null, LocalDateTime.of(2022, 6, 21, 13, 15),
                        LocalDateTime.of(2022, 6, 21, 14, 0), null));
                appointmentRepository.save(new Appointment(true, practitioners.get(i), null, LocalDateTime.of(2022, 6, 22, 10, 0),
                        LocalDateTime.of(2022, 6, 22, 10, 45), null));
                appointmentRepository.save(new Appointment(true, practitioners.get(i), null, LocalDateTime.of(2022, 6, 22, 10, 45),
                        LocalDateTime.of(2022, 6, 22, 11, 30), null));
                appointmentRepository.save(new Appointment(true, practitioners.get(i), null, LocalDateTime.of(2022, 6, 23, 10, 30),
                        LocalDateTime.of(2022, 6, 23, 11, 15), null));
                appointmentRepository.save(new Appointment(true, practitioners.get(i), null, LocalDateTime.of(2022, 6, 24, 8, 0),
                        LocalDateTime.of(2022, 6, 24, 8, 45), null));
                appointmentRepository.save(new Appointment(true, practitioners.get(i), null, LocalDateTime.of(2022, 6, 25, 14, 0),
                        LocalDateTime.of(2022, 6, 20, 14, 45), null));
            }
            //booked appointments
            for (int i = 0; i < users.size(); i++) {
                appointmentRepository.save(new Appointment(false, practitioners.get(0), users.get(i), LocalDateTime.of(2022, 7, 12, 10, 45),
                        LocalDateTime.of(2022, 7, 12, 11, 30), null));
                appointmentRepository.save(new Appointment(false, practitioners.get(1), users.get(i), LocalDateTime.of(2022, 7, 20, 8, 45),
                        LocalDateTime.of(2022, 7, 12, 9, 30), null));
            }
        }
    }
}
