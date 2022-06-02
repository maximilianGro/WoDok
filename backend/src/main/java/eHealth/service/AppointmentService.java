package eHealth.service;

import eHealth.dto.AppointmentDto;
import eHealth.dto.AppointmentSimpleDto;
import eHealth.entity.Appointment;

import java.util.List;

public interface AppointmentService {

    List<AppointmentDto> getByEmail(String email);

    Appointment create(AppointmentSimpleDto appointmentSimpleDto);
}
