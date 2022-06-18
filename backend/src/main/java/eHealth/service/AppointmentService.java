package eHealth.service;

import eHealth.dto.QueueDto;
import eHealth.entity.Appointment;

import java.util.List;

public interface AppointmentService {

    List<Appointment> getAppointmentByPatientId(Long patientId);

    void bookAppointment(Appointment appointment);

    List<Appointment> getFreeByPractitionerId(Long id);

    void addInQueue(QueueDto queueDto);
}
