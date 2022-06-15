package eHealth.service;


//import eHealth.entity.Practitioner;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles({"test", "datagen"}) // enable "test" spring profile during test execution in order to pick up configuration from application-test.yml
@SpringBootTest
public class PractitionerServiceTest {

//    @Autowired
//    PractitionerService practitionerService;
//
//    @Test
//    public void getAllReturnsAllStoredPractitioners() {
//        List<Practitioner> practitioner = practitionerService.allPractitioners();
//        assertThat(practitioner.size()).isEqualTo(3);
//        assertThat(practitioner.get(0).getId()).isEqualTo(-3);
//        assertThat(practitioner.get(0).getName()).isEqualTo("Dr. Kellner");
//    }
}
