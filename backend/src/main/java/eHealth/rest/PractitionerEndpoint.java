package eHealth.rest;

import eHealth.dto.PractitionerDto;
import eHealth.entity.Practitioner;
import eHealth.mapper.PractitionerMapper;
import eHealth.service.PractitionerService;
import eHealth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.lang.invoke.MethodHandles;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/practitioners")
public class PractitionerEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final PractitionerService service;
    private final UserService userService;
    private final PractitionerMapper mapper;

    public PractitionerEndpoint(PractitionerService service, UserService userService, PractitionerMapper mapper) {
        this.service = service;
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping
    public Stream<PractitionerDto> allPractitioners() {
        LOGGER.info("HAllO: " + userService.getAll());
        return service.allPractitioners().stream().map(mapper::entityToDto);
    }

    @GetMapping(value = "/{practitionerId}")
    @ResponseStatus(HttpStatus.OK)
    @PermitAll
    public PractitionerDto getPractitionerById(@PathVariable("practitionerId") Long practitionerId) {
        LOGGER.info("Get practitioner Details: " + practitionerId);
        return mapper.entityToDto(this.service.getPractitionerById(practitionerId));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PractitionerDto createPractitioner(@RequestBody PractitionerDto practitionerDto) {
        return mapper.entityToDto(this.service.createPractitioner(mapper.dtoToEntity(practitionerDto)));
    }

    @PutMapping(value = "/{practitionerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PractitionerDto updatePractitioner(@PathVariable("practitionerId") Long practitionerId, @RequestBody PractitionerDto practitionerDto) {
        Practitioner input = mapper.dtoToEntity(practitionerDto);
        input.setId(practitionerId);
        return mapper.entityToDto(this.service.updatePractitioner(input));
    }
}
