package eHealth.rest;

import eHealth.mapper.PractitionerMapper;
import eHealth.dto.PractitionerDto;
import eHealth.service.PractitionerService;
import eHealth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/practitioners")
public class PractitionerEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final PractitionerService service;
    private final UserService userService;
    private final PractitionerMapper mapper;

    public PractitionerEndpoint(PractitionerService service, UserService  userService, PractitionerMapper mapper) {
        this.service = service;
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping
    public Stream<PractitionerDto> allPractitioners() {
        LOGGER.info("HAllO: " + userService.getAll());
        return service.allPractitioners().stream().map(mapper::entityToDto);
    }
}
