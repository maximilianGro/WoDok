package eHealth.service;

import eHealth.entity.Appointment;

import java.util.List;

public interface AppointmentService {

    List<Appointment> getAppointmentByPatientId(Long patientId);

    void bookAppointment(Appointment appointment);

    List<Appointment> getFreeByPractitionerId(Long id);
}
