package eHealth.service.impl;

import eHealth.Repository.PractitionerRepository;
import eHealth.entity.Practitioner;
import eHealth.persistence.PractitionerDao;
import eHealth.service.PractitionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PractitionerServiceImpl implements PractitionerService {
    private final PractitionerRepository practitionerRepository;


    public PractitionerServiceImpl(PractitionerRepository practitionerRepository) {
        this.practitionerRepository = practitionerRepository;
    }

    @Override
    public List<Practitioner> allPractitioners() {
        return practitionerRepository.findAll();
    }

    @Override
    public Practitioner getPractitionerById(Long practitionerId) {

        Optional<Practitioner> practitionerOptional = practitionerRepository.findById(practitionerId);
        return practitionerOptional.get();
    }

    @Override
    public Practitioner createPractitioner(Practitioner practitioner) {
        return practitionerRepository.save(practitioner);
    }
}
