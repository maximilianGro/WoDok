package eHealth.rest;

import eHealth.dto.AppointmentDto;
import eHealth.dto.QueueDto;
import eHealth.dto.UserRegisterDto;
import eHealth.mapper.AppointmentMapper;
import eHealth.service.AppointmentService;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.PermitAll;
import java.lang.invoke.MethodHandles;
import java.util.List;

@RestController
@RequestMapping(AppointmentEndpoint.BASE_URL)
public class AppointmentEndpoint {
    public static final String BASE_URL = "/appointments";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final AppointmentService service;
    private final AppointmentMapper mapper;

    @Autowired
    public AppointmentEndpoint(AppointmentService service, AppointmentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/byAppointment/{appointmentId}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentDto getAppointmentById(@PathVariable Long appointmentId) {
        LOGGER.info("GET: " + BASE_URL + "/" + appointmentId);
        return mapper.entityToDto(service.getAppointmentById(appointmentId));
    }

    @GetMapping("/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDto> getAppointmentByPatientId(@PathVariable Long patientId) {
        LOGGER.info("GET: " + BASE_URL + "/" + patientId);
        return mapper.entityToDto(service.getAppointmentByPatientId(patientId));
    }

    @GetMapping("/free/{practitionerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDto> getFreeAppointmentByPractitionerId(@PathVariable Long practitionerId) {
        LOGGER.info("GET: " + BASE_URL + "/free/" + " " + practitionerId);
        return mapper.entityToDto(service.getFreeByPractitionerId(practitionerId));
    }

    @PostMapping("free")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean bookAppointment(@RequestBody AppointmentDto appointmentDto) {
        LOGGER.info("POST:" + BASE_URL + "/" + " " + appointmentDto);
        service.bookAppointment(mapper.dtoToEntity(appointmentDto));
        return true;
    }

    @PostMapping("/queue")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean addInQueue(@RequestBody QueueDto queueDto) {
        LOGGER.info("POST:" + BASE_URL + "/" + " " + queueDto);
        service.addInQueue(queueDto);
        return true;
    }

    @DeleteMapping("/{practitionerId}")
    public boolean deleteAll(@PathVariable Long practitionerId) {
        LOGGER.info("DELETE " + BASE_URL + "/{}", practitionerId);
        try {
            this.service.deleteAll(practitionerId);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not delete all: " + e.getLocalizedMessage(), e);
        }
        return true;
    }

//    @DeleteMapping("/queue")
//    @ResponseStatus
}
