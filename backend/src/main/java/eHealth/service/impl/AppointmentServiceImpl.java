package eHealth.service.impl;

import eHealth.Repository.AppointmentRepository;
import eHealth.Repository.UserRepository;
import eHealth.dto.AppointmentDto;
import eHealth.dto.AppointmentSimpleDto;
import eHealth.entity.Appointment;
import eHealth.entity.User;
import eHealth.exception.NotFoundException;
import eHealth.exception.PersistenceException;
import eHealth.mapper.AppointmentMapper;
import eHealth.service.AppointmentService;
import eHealth.service.UserService;
import org.hibernate.service.spi.ServiceException;
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
    private final AppointmentMapper appointmentMapper;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper, UserRepository userRepository, UserService userService) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public List<AppointmentDto> getByEmail(String email) {
        User user = this.userService.findUserByEmail(email);
        if (user == null) {
            throw new NotFoundException(String.format("Could not find the user with the email address %s", email));
        }
        List<AppointmentDto> appointments = appointmentMapper.entityToDto(appointmentRepository.findAppointmentsByPatient(user));
        return appointments;
    }

    @Override
    public Appointment create(AppointmentSimpleDto appointmentSimpleDto) {
        Appointment appointment = new Appointment();
        try {
            appointment.setPractitioner(userRepository.getById(appointmentSimpleDto.getPractitionerId()));
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        }
        try {
            appointment.setPatient(userRepository.getById(appointmentSimpleDto.getPatientId()));
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        }
        appointment.setDate(appointmentSimpleDto.getDate());
        try {
            return appointmentRepository.save(appointment);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
