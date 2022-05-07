package eHealth.rest;

import eHealth.mapper.PractitionerMapper;
import eHealth.dto.PractitionerDto;
import eHealth.service.PractitionerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/practitioners")
public class PractitionerEndpoint {
    private final PractitionerService service;
    private final PractitionerMapper mapper;

    public PractitionerEndpoint(PractitionerService service, PractitionerMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public Stream<PractitionerDto> allPractitioners() {
        return service.allPractitioners().stream()
                .map(mapper::entityToDto);
    }
}
