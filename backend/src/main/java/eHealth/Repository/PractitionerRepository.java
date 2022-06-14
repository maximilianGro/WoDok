package eHealth.Repository;

import eHealth.entity.Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PractitionerRepository extends JpaRepository<Practitioner, Long> {

    Optional<Practitioner> findById(Long id);

    List<Practitioner> findBySpecialty(String speciality);

}
