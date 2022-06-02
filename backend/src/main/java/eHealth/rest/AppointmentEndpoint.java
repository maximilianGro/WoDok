package eHealth.rest;

import eHealth.dto.AppointmentDto;
import eHealth.dto.AppointmentSimpleDto;
import eHealth.entity.Appointment;
import eHealth.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.lang.invoke.MethodHandles;
import java.util.List;

@RestController
@RequestMapping(AppointmentEndpoint.BASE_URL)
public class AppointmentEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final String BASE_URL = "/appointments";
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentEndpoint(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PermitAll
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@RequestBody AppointmentSimpleDto appointmentSimpleDto) {
        LOGGER.info("POST " + BASE_URL + " " + appointmentSimpleDto.toString());
        appointmentService.create(appointmentSimpleDto);
    }

    /**
     * Gets all appointments for a specific UserEmail.
     */
    @GetMapping("/{email}")
    @PermitAll
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDto> getAppointmentByEmail(@PathVariable String email) {
        LOGGER.info("GET " + BASE_URL + "/{}", email);
        return appointmentService.getByEmail(email);
    }
}
