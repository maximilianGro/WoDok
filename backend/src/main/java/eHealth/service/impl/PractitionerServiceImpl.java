package eHealth.service.impl;

import eHealth.Repository.PractitionerRepository;
import eHealth.entity.Practitioner;
import eHealth.persistence.PractitionerDao;
import eHealth.service.PractitionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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

    @Override
    public Practitioner updatePractitioner(Practitioner practitioner) {
        return practitionerRepository.save(practitioner);
    }

    @Override
    public List<Practitioner> searchBySpecialtyAndAddressAndOpeningHours(String speciality, String address, String openingHours) {
        Practitioner practitioner = new Practitioner();
        practitioner.setSpecialty(speciality);
        practitioner.setAddress(address);
        practitioner.setOpeningHours(openingHours);
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Practitioner> example = Example.of(practitioner, exampleMatcher);
        return practitionerRepository.findAll(example);
    }


}
