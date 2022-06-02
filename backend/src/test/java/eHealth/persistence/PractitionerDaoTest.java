package eHealth.persistence;

import eHealth.entity.Practitioner;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles({"test", "datagen"}) // enable "test" spring profile during test execution in order to pick up configuration from application-test.yml
@SpringBootTest
public class PractitionerDaoTest {

    @Autowired
    PractitionerDao practitionerDao;

    @Test
    public void getAllReturnsAllStoredPractitioners() {
        List<Practitioner> practitioners = practitionerDao.getAll();
        assertThat(practitioners.size()).isEqualTo(3);
        assertThat(practitioners.get(0).getId()).isEqualTo(-3);
        assertThat(practitioners.get(0).getFirstName()).isEqualTo("Dr. Kellner");
    }
}
