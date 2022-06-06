package eHealth.service;

import eHealth.entity.Practitioner;
import org.springframework.beans.PropertyValues;

import java.util.List;

/**
 * Service for working with practitioners.
 */
public interface PractitionerService {
    /**
     * Lists all practitioners stored in the system.
     *
     * @return list of all stored practitioners
     */
    List<Practitioner> allPractitioners();

    Practitioner getPractitionerById(Long practitionerId);

    Practitioner createPractitioner(Practitioner practitioner);

    Practitioner updatePractitioner(Practitioner practitioner);

    List<Practitioner> searchBySpecialtyAndAddressAndOpeningHours(String speciality, String address, String openingHours);

}
