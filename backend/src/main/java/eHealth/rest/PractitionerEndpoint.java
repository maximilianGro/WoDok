package eHealth.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import eHealth.dto.QuestionnaireDto;
import eHealth.entity.Practitioner;
import eHealth.mapper.PractitionerMapper;
import eHealth.dto.PractitionerDto;
import eHealth.mapper.QuestionaireMapper;
import eHealth.service.PractitionerService;
import eHealth.service.UserService;
import org.apache.tomcat.util.json.JSONParser;
import org.h2.util.json.JSONArray;
import org.h2.util.json.JSONObject;
import org.h2.util.json.JSONString;
import org.h2.util.json.JSONStringSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.PermitAll;
import javax.management.Query;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/practitioners")
public class PractitionerEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final PractitionerService service;
    private final UserService userService;
    private final PractitionerMapper mapper;
    private final QuestionaireMapper mapperQ;

    public PractitionerEndpoint(PractitionerService service, UserService userService, PractitionerMapper mapper, QuestionaireMapper q) {
        this.service = service;
        this.userService = userService;
        this.mapper = mapper;
        this.mapperQ = q;
    }

    @GetMapping("/suche")
    public Stream<PractitionerDto> findPractitioners(@RequestParam(required = false) String speciality, @RequestParam(required = false) String address, @RequestParam(required = false) String openingHours) {
        if (speciality == null && address == null && openingHours == null) {
            return service.allPractitioners().stream().map(mapper::entityToDto);

        }
        return service.searchBySpecialtyAndAddressAndOpeningHours(speciality, address, openingHours).stream().map(mapper::entityToDto);
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

    @PostMapping(value = "/questionnaire")
    public Stream<PractitionerDto> questionnaire(@RequestBody QuestionnaireDto input) {
        LOGGER.info("Post Questionnaire" + input);
        return this.service.questionnaire(mapperQ.dtoToEntity(input)).stream().map(mapper::entityToDto);
    }
}
