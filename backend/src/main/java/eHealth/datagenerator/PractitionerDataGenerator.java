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
                    "Mo-Fr 9:00-13:00", "Albertgasse 44/2, 1080 Wien", "06601234567", "drkoenig@ordination.com"));
            practitionerRepository.save(new Practitioner("Dr. Gustav",
                    "Maier", "Zahnarzt",
                    "Di-Do 8:00-13:00", "Kirchengasse 12, 1070 Wien", "066458640385", "drmaier@ordination.com"));
            practitionerRepository.save(new Practitioner("Dr. Jutta",
                    "Schneller", "Zahnarzt",
                    "Mo-Mi 10:00-14:00, Do-Fr 16:00-18:00", "Neubaugasse 55/2, 1070 Wien", "067639539524", "drschneller@ordination.com"));
            practitionerRepository.save(new Practitioner("Dr. Josef",
                    "Hofer", "Zahnarzt",
                    "Mo-Fr 9:00-13:00 Di 18:00-20:00", "Bennoplatz 3, 1080 Wien", "066034293523", "drhofer@ordination.com"));
            practitionerRepository.save(new Practitioner("Dr. Melanie",
                    "Dienbauer", "Zahnarzt",
                    "Di-Fr 8:00-13:00 Mo-Mi 15:00-18:00", "Roßauer Lände 32/3, 1090 Wien", "0664452465343", "drdienbauer@ordination.com"));
            practitionerRepository.save(new Practitioner("Dr. Robert",
                    "Weber", "Zahnarzt",
                    "Mo-Do 8:00-13:00", "Berggasse 32/3, 1090 Wien", "066034634624", "drweber@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Markus",
                "David", "Zahnarzt",
                "Mo-Fr 9:00-13:00", "Rotenturmstraße 32/4, 1010 Wien", "066034634624", "drdavid@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Oliver",
                "Rainer", "Zahnarzt",
                "Di-Do 10:00-13:00", "Vorgartenstraße 32/3, 1020 Wien", "066034634624", "drrainer@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Susanne",
                "Reis", "Zahnarzt",
                "Mi-Fr 8:00-13:00, Do 16:00-20:00", "Erdbergstraße 32/3, 1030 Wien", "066034634624", "drreis@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Robert",
                "Lorenz", "Zahnarzt",
                "Mo-Fr 9:00-14:00, Mo-Di 17:00-19:00", "Südtiroler Platz 32/3, 1040 Wien", "066034634624", "drlorenz@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Maria",
                "Reiter", "Zahnarzt",
                "Mo-Do 8:00-13:00", "Krongasse 23/4, 1050 Wien", "066034634624", "drreiter@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Lisa",
                "Prenner", "Zahnarzt",
                "Mo-Mi 10:00-13:00, Mi-Fr 17:00-19:00", "Lehargasse 23/3, 1060 Wien", "066034634624", "drprenner@ordination.com"));

            //Gynäkologe
        practitionerRepository.save(new Practitioner("Dr. Robert",
                "Huber", "Gynäkologe",
                "Mo-Fr 9:00-13:00", "Mariahilfer Straße 132, 1070 Wien", "06601234567", "drhuber@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Werner",
                "Roller", "Gynäkologe",
                "Di-Do 8:00-13:00", "Josefstädter Straße 34, 1080 Wien", "066458640385", "drroller@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Alexander",
                "Kainz", "Gynäkologe",
                "Mo-Mi 10:00-14:00, Do-Fr 16:00-18:00", "Breitenfelder Gasse 23, 1080 Wien", "067639539524", "drkainz@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Johann",
                "Hofer", "Gynäkologe",
                "Mo-Fr 9:00-13:00 Di 18:00-20:00", "Lindengasse 44, 1070 Wien", "066034293523", "drhofer@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Christina",
                "Müller", "Gynäkologe",
                "Di-Fr 8:00-13:00 Mo-Mi 15:00-18:00", "Nordbergtraße 4/2, 1090 Wien", "0664452465343", "drmueller@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Alexandra",
                "Aigner", "Gynäkologe",
                "Mo-Do 8:00-14:00", "Kolingasse 54/3, 1090 Wien", "066034634624", "draigner@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Markus",
                "David", "Gynäkologe",
                "Mo-Fr 9:00-13:00", "Rotenturmstraße 32/4, 1010 Wien", "066034634624", "drdavid@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Oliver",
                "Rainer", "Gynäkologe",
                "Di-Do 10:00-13:00", "Vorgartenstraße 32/3, 1020 Wien", "066034634624", "drrainer@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Susanne",
                "Reis", "Gynäkologe",
                "Mi-Fr 8:00-13:00, Do 16:00-20:00", "Erdbergstraße 32/3, 1030 Wien", "066034634624", "drreis@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Robert",
                "Lorenz", "Gynäkologe",
                "Mo-Fr 9:00-14:00, Mo-Di 17:00-19:00", "Südtiroler Platz 32/3, 1040 Wien", "066034634624", "drlorenz@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Maria",
                "Reiter", "Gynäkologe",
                "Mo-Do 8:00-13:00", "Krongasse 23/4, 1050 Wien", "066034634624", "drreiter@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Lisa",
                "Prenner", "Gynäkologe",
                "Mo-Mi 10:00-13:00, Mi-Fr 17:00-19:00", "Lehargasse 23/3, 1060 Wien", "066034634624", "drprenner@ordination.com"));

        //Urologe
        practitionerRepository.save(new Practitioner("Dr. Vincent",
                "Trummer", "Urologe",
                "Mo-Fr 9:00-14:00", "Albertgasse 33/4, 1080 Wien", "06601234567", "drtrummer@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Elisa",
                "Bischof", "Urologe",
                "Di-Do 8:00-13:00", "Piaristengasse 45, 1080 Wien", "066458640385", "drbischof@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Daniel",
                "Krautsack", "Urologe",
                "Mo-Mi 10:00-14:00, Do-Fr 16:00-18:00", "Müllnergasse 42, 1090 Wien", "067639539524", "drkrautsack@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Natalie",
                "Seemann", "Urologe",
                "Mo-Fr 9:00-14:00 Di 18:00-20:00", "Kolingasse 53/4, 1090 Wien", "066034293523", "drseemann@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Christina",
                "Maierhofer", "Urologe",
                "Di-Fr 8:00-13:00 Mo-Mi 15:00-18:00", "Mariahilfer Straße 89, 1070 Wien", "0664452465343", "drmaierhofer@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Birgit",
                "Sturm", "Urologe",
                "Mo-Do 8:00-13:00", "Schottenfeldgasse 32/4, 1070 Wien", "066034634624", "drsturm@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Markus",
                "David", "Urologe",
                "Mo-Fr 9:00-13:00", "Rotenturmstraße 32/4, 1010 Wien", "066034634624", "drdavid@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Oliver",
                "Rainer", "Urologe",
                "Di-Do 10:00-13:00", "Vorgartenstraße 32/3, 1020 Wien", "066034634624", "drrainer@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Susanne",
                "Reis", "Urologe",
                "Mi-Fr 8:00-13:00, Do 16:00-20:00", "Erdbergstraße 32/3, 1030 Wien", "066034634624", "drreis@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Robert",
                "Lorenz", "Urologe",
                "Mo-Fr 9:00-14:00, Mo-Di 17:00-19:00", "Südtiroler Platz 32/3, 1040 Wien", "066034634624", "drlorenz@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Maria",
                "Reiter", "Urologe",
                "Mo-Do 8:00-13:00", "Krongasse 23/4, 1050 Wien", "066034634624", "drreiter@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Lisa",
                "Prenner", "Urologe",
                "Mo-Mi 10:00-13:00, Mi-Fr 17:00-19:00", "Lehargasse 23/3, 1060 Wien", "066034634624", "drprenner@ordination.com"));

        //Dermatologe
        practitionerRepository.save(new Practitioner("Dr. Tamara",
                "Trummer", "Dermatologe",
                "Mo-Fr 9:00-14:00", "Albertgasse 33/4, 1080 Wien", "06601234567", "drtrummer@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Tanja",
                "Bischof", "Dermatologe",
                "Di-Do 8:00-13:00", "Piaristengasse 45, 1080 Wien", "066458640385", "drbischof@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Bernd",
                "Krautsack", "Dermatologe",
                "Mo-Mi 10:00-14:00, Do-Fr 16:00-18:00", "Müllnergasse 42, 1090 Wien", "067639539524", "drkrautsack@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Livia",
                "Sedas", "Dermatologe",
                "Mo-Fr 9:00-13:00 Di 18:00-20:00", "Kolingasse 53/4, 1090 Wien", "066034293523", "drseemann@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Mario",
                "Maierhofer", "Dermatologe",
                "Di-Fr 8:00-13:00 Mo-Mi 15:00-18:00", "Mariahilfer Straße 89, 1070 Wien", "0664452465343", "drmaierhofer@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Leonie",
                "Fuchs", "Dermatologe",
                "Mo-Do 8:00-13:00", "Schottenfeldgasse 32/4, 1070 Wien", "066034634624", "drfuchs@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Markus",
                "David", "Dermatologe",
                "Mo-Fr 9:00-13:00", "Rotenturmstraße 32/4, 1010 Wien", "066034634624", "drdavid@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Oliver",
                "Rainer", "Dermatologe",
                "Di-Do 10:00-13:00", "Vorgartenstraße 32/3, 1020 Wien", "066034634624", "drrainer@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Susanne",
                "Reis", "Dermatologe",
                "Mi-Fr 8:00-13:00, Do 16:00-20:00", "Erdbergstraße 32/3, 1030 Wien", "066034634624", "drreis@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Robert",
                "Lorenz", "Dermatologe",
                "Mo-Fr 9:00-14:00, Mo-Di 17:00-19:00", "Südtiroler Platz 32/3, 1040 Wien", "066034634624", "drlorenz@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Maria",
                "Reiter", "Dermatologe",
                "Mo-Do 8:00-13:00", "Krongasse 23/4, 1050 Wien", "066034634624", "drreiter@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Lisa",
                "Prenner", "Dermatologe",
                "Mo-Mi 10:00-13:00, Mi-Fr 17:00-19:00", "Lehargasse 23/3, 1060 Wien", "066034634624", "drprenner@ordination.com"));

        //Kardiologe
        practitionerRepository.save(new Practitioner("Dr. Michael",
                "Schiller", "Kardiologe",
                "Mo-Fr 9:00-15:00", "Albertgasse 33/4, 1080 Wien", "06601234567", "drschiller@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Nico",
                "Beck", "Kardiologe",
                "Di-Do 8:00-13:00", "Piaristengasse 45, 1080 Wien", "066458640385", "drbeck@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Martin",
                "Richter", "Kardiologe",
                "Mo-Mi 10:00-14:00, Do-Fr 16:00-18:00", "Müllnergasse 42, 1090 Wien", "067639539524", "drrichter@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Natalie",
                "Weinmann", "Kardiologe",
                "Mo-Fr 9:00-14:00 Di 18:00-20:00", "Kolingasse 53/4, 1090 Wien", "066034293523", "drweinmann@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Christina",
                "Engel", "Kardiologe",
                "Di-Fr 8:00-13:00 Mo-Mi 15:00-18:00", "Mariahilfer Straße 89, 1070 Wien", "0664452465343", "drengelhofer@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Birgit",
                "Ress", "Kardiologe",
                "Mo-Do 8:00-13:00", "Schottenfeldgasse 32/4, 1070 Wien", "066034634624", "drress@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Markus",
                "David", "Kardiologe",
                "Mo-Fr 9:00-13:00", "Rotenturmstraße 32/4, 1010 Wien", "066034634624", "drdavid@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Oliver",
                "Rainer", "Kardiologe",
                "Di-Do 10:00-13:00", "Vorgartenstraße 32/3, 1020 Wien", "066034634624", "drrainer@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Susanne",
                "Reis", "Kardiologe",
                "Mi-Fr 8:00-13:00, Do 16:00-20:00", "Erdbergstraße 32/3, 1030 Wien", "066034634624", "drreis@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Robert",
                "Lorenz", "Kardiologe",
                "Mo-Fr 9:00-14:00, Mo-Di 17:00-19:00", "Südtiroler Platz 32/3, 1040 Wien", "066034634624", "drlorenz@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Maria",
                "Reiter", "Kardiologe",
                "Mo-Do 8:00-13:00", "Krongasse 23/4, 1050 Wien", "066034634624", "drreiter@ordination.com"));
        practitionerRepository.save(new Practitioner("Dr. Lisa",
                "Prenner", "Kardiologe",
                "Mo-Mi 10:00-13:00, Mi-Fr 17:00-19:00", "Lehargasse 23/3, 1060 Wien", "066034634624", "drprenner@ordination.com"));

    }
}
