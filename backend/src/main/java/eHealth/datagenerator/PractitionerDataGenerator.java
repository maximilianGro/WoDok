package eHealth.datagenerator;

import eHealth.Repository.PractitionerRepository;
import eHealth.entity.Practitioner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.invoke.MethodHandles;

@Profile("generateData")
@Component
public class PractitionerDataGenerator implements DataGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final PractitionerRepository practitionerRepository;

    public PractitionerDataGenerator(PractitionerRepository practitionerRepository) {
        this.practitionerRepository = practitionerRepository;
    }

    @Override
    @PostConstruct
    public void generate() {
    LOGGER.debug("Generating practitioners...");
            practitionerRepository.deleteAll();
            practitionerRepository.flush();
            practitionerRepository.save(new Practitioner("Dr. Maria",
                    "König", "Zahnarzt",
                    "Mo-Fr 9:00-12:00", "Albertgasse 44/2, 1080 Wien", "06601234567", "drkoenig@ordination.com"));
            practitionerRepository.save(new Practitioner("Dr. Gustav",
                    "Maier", "Zahnarzt",
                    "Di-Do 8:00-13:00", "Kirchengasse 12, 1070 Wien", "066458640385", "drmaier@ordination.com"));
            practitionerRepository.save(new Practitioner("Dr. Jutta",
                    "Schneller", "Zahnarzt",
                    "Mo-Mi 10:00-14:00, Do-Fr 16:00-18:00", "Neubaugasse 55/2, 1070 Wien", "067639539524", "drschneller@ordination.com"));
            practitionerRepository.save(new Practitioner("Dr. Josef",
                    "Hofer", "Zahnarzt",
                    "Mo-Fr 9:00-10:00 Di 18:00-20:00", "Bennoplatz 3, 1080 Wien", "066034293523", "drhofer@ordination.com"));
            practitionerRepository.save(new Practitioner("Dr. Melanie",
                    "Dienbauer", "Zahnarzt",
                    "Di-Fr 8:00-12:00 Mo-Mi 15:00-18:00", "Roßauer Lände 32/3, 1090 Wien", "0664452465343", "drdienbauer@ordination.com"));
            practitionerRepository.save(new Practitioner("Dr. Robert",
                    "Weber", "Zahnarzt",
                    "Mo-Do 7:00-11:00", "Berggasse 32/3, 1090 Wien", "066034634624", "drweber@ordination.com"));

            //Gynäkologe
        practitionerRepository.save(new Practitioner("Dr. Robert",
                "Huber", "Gynäkologe",
                "Mo-Fr 9:00-12:00", "Mariahilfer Straße 132, 1070 Wien", "06601234567", "drhuber@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Werner",
                "Roller", "Gynäkologe",
                "Di-Do 8:00-13:00", "Josefstädter Straße 34, 1080 Wien", "066458640385", "drroller@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Alexander",
                "Kainz", "Gynäkologe",
                "Mo-Mi 10:00-14:00, Do-Fr 16:00-18:00", "Breitenfelder Gasse 23, 1080 Wien", "067639539524", "drkainz@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Johann",
                "Hofer", "Gynäkologe",
                "Mo-Fr 9:00-10:00 Di 18:00-20:00", "Lindengasse 44, 1070 Wien", "066034293523", "drhofer@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Christina",
                "Müller", "Gynäkologe",
                "Di-Fr 8:00-12:00 Mo-Mi 15:00-18:00", "Nordbergtraße 4/2, 1090 Wien", "0664452465343", "drmueller@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Alexandra",
                "Aigner", "Gynäkologe",
                "Mo-Do 7:00-11:00", "Kolingasse 54/3, 1090 Wien", "066034634624", "draigner@ordination.com"));

        //Orthopäde
        practitionerRepository.save(new Practitioner("Dr. Vincent",
                "Trummer", "Orthopäde",
                "Mo-Fr 9:00-12:00", "Albertgasse 33/4, 1080 Wien", "06601234567", "drtrummer@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Elisa",
                "Bischof", "Orthopäde",
                "Di-Do 8:00-13:00", "Piaristengasse 45, 1080 Wien", "066458640385", "drbischof@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Daniel",
                "Krautsack", "Orthopäde",
                "Mo-Mi 10:00-14:00, Do-Fr 16:00-18:00", "Müllnergasse 42, 1090 Wien", "067639539524", "drkrautsack@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Natalie",
                "Seemann", "Orthopäde",
                "Mo-Fr 9:00-10:00 Di 18:00-20:00", "Kolingasse 53/4, 1090 Wien", "066034293523", "drseemann@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Christina",
                "Maierhofer", "Orthopäde",
                "Di-Fr 8:00-12:00 Mo-Mi 15:00-18:00", "Mariahilfer Straße 89, 1070 Wien", "0664452465343", "drmaierhofer@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Birgit",
                "Sturm", "Orthopäde",
                "Mo-Do 7:00-11:00", "Schottenfeldgasse 32/4, 1070 Wien", "066034634624", "drsturm@ordination.com"));



    }
}
