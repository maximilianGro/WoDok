package eHealth.service.impl;

import eHealth.Repository.AppointmentRepository;
import eHealth.Repository.QueueRepository;
import eHealth.dto.QueueDto;
import eHealth.entity.Appointment;
import eHealth.entity.Practitioner;
import eHealth.entity.Queue;
import eHealth.entity.User;
import eHealth.exception.NotFoundException;
import eHealth.service.AppointmentService;
import eHealth.service.PractitionerService;
import eHealth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final AppointmentRepository appointmentRepository;
    private final PractitionerService practitionerService;
    private final UserService userService;
    private final QueueRepository queueRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, PractitionerService practitionerService, UserService userService, QueueRepository queueRepository) {
        this.appointmentRepository = appointmentRepository;
        this.practitionerService = practitionerService;
        this.userService = userService;
        this.queueRepository = queueRepository;
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

    @Override
    public void addInQueue(QueueDto queueDto) {
        Queue queue = new Queue();
        Practitioner practitioner = practitionerService.getPractitionerById(queueDto.getPractitionerId());
        User user = userService.getUserById(queueDto.getUserId());

        queue.setPractitioner(practitioner);
        queue.setUser(user);
        queue.setTimeStamp(LocalDateTime.now());

        queueRepository.save(queue);
    }
}
