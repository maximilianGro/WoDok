package eHealth.mapper;

import eHealth.dto.AppointmentDto;
import eHealth.entity.Appointment;
import eHealth.entity.Practitioner;
import eHealth.entity.User;
import eHealth.service.PractitionerService;
import eHealth.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentMapper {
    private final UserService userService;
    private final PractitionerService practitionerService;

    @Autowired
    public AppointmentMapper(UserService userService, PractitionerService practitionerService) {
        this.userService = userService;
        this.practitionerService = practitionerService;
    }

    public AppointmentDto entityToDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(appointment.getId());
        appointmentDto.setFreeAppointment(appointment.getFreeAppointment());
        Practitioner practitioner = appointment.getPractitioner();
        if (practitioner != null) {
            appointmentDto.setPractitionerId(practitioner.getId());
        }
        User patient = appointment.getPatient();
        if (patient != null) {
            appointmentDto.setPatientId(patient.getId());
        }
        appointmentDto.setStart(appointment.getStart());
        appointmentDto.setEnd(appointment.getEnd());
        appointmentDto.setPatientDescription(appointment.getPatientDescription());
        return appointmentDto;
    }

    public List<AppointmentDto> entityToDto(List<Appointment> appointments) {
        if (appointments == null) {
            return null;
        }
        List<AppointmentDto> list = new ArrayList<>();
        for (Appointment appointment : appointments) {
            list.add(this.entityToDto(appointment));
        }
        return list;
    }

    public Appointment dtoToEntity(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        appointment.setId(appointmentDto.getId());
        appointment.setFreeAppointment(appointment.getFreeAppointment());
        appointment.setPractitioner(practitionerService.getPractitionerById(appointmentDto.getPractitionerId()));
        appointment.setPatient(userService.getUserById(appointmentDto.getPatientId()));
        appointment.setStart(appointmentDto.getStart());
        appointment.setEnd(appointmentDto.getEnd());
        appointment.setPatientDescription(appointmentDto.getPatientDescription());
        return appointment;
    }
}
