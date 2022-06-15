package eHealth.service.impl;

import eHealth.Repository.AppointmentRepository;
import eHealth.entity.Appointment;
import eHealth.exception.NotFoundException;
import eHealth.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> getAppointmentByPatientId(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findAllByPatientId(patientId);
        if (appointments.isEmpty()) {
            throw new NotFoundException("Could not find appointments for patient with id " + patientId);
        }
        return appointments;
    }

    @Override
    public List<Appointment> getFreeByPractitionerId(Long id) {
        List<Appointment> freeAppointments = appointmentRepository.findFreeByPractitionerId(id);
        if (freeAppointments == null) {
            throw new NotFoundException(String.format("Could not find free appointments for practitioner with id %s.", id));
        }
        return freeAppointments;
    }

    @Override
    public void bookAppointment(Appointment appointment) {
        int n = appointmentRepository.bookAppointment(appointment.getId(), appointment.getPatient(), appointment.getPatientDescription());
        if (n == 0) {
            throw new NotFoundException("Could not book appointment with id " + appointment.getId());
        }
    }
}
