package eHealth.mapper;

import eHealth.dto.PractitionerDto;
import eHealth.entity.Practitioner;
import org.springframework.stereotype.Component;

@Component
public class PractitionerMapper {

    public PractitionerDto entityToDto(Practitioner practitioner) {
        return new PractitionerDto(practitioner.getId(), practitioner.getFirstName(), practitioner.getLastName(), practitioner.getSpecialty(), practitioner.getOpeningHours(), practitioner.getAddress(), practitioner.getPhone(), practitioner.getEmail());
    }

    public Practitioner dtoToEntity(PractitionerDto practitionerDto) {
        return new Practitioner(practitionerDto.id(), practitionerDto.firstName(), practitionerDto.lastName(), practitionerDto.specialty(), practitionerDto.openingHours(), practitionerDto.address(), practitionerDto.phone(), practitionerDto.email());
    }
}
