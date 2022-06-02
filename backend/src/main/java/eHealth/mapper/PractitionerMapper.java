package eHealth.mapper;

import eHealth.dto.PractitionerDto;
import eHealth.entity.Practitioner;
import org.springframework.stereotype.Component;

@Component
public class PractitionerMapper {

    public PractitionerDto entityToDto(Practitioner practitioner) {
        return new PractitionerDto(practitioner.getId(), practitioner.getFirstName(), practitioner.getLastName());
    }

    public Practitioner dtoToEntity(PractitionerDto practitionerDto) {
        return new Practitioner(practitionerDto.id(),practitionerDto.firstName(),practitionerDto.lastName());
    }
}
