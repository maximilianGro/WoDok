package eHealth.mapper;

import eHealth.dto.AppointmentDto;
import eHealth.entity.Appointment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentMapper {
    private final UserMapper userMapper;
    
    public AppointmentMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public AppointmentDto entityToDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setDate(appointment.getDate());
        appointmentDto.setPatient(userMapper.UserToUserRegisterDto(appointment.getPatient()));
        appointmentDto.setPractitioner(userMapper.UserToUserRegisterDto(appointment.getPractitioner()));
        return appointmentDto;
    }
    
    public List<AppointmentDto> entityToDto(List<Appointment> appointments) {
        if (appointments == null) {
            return null;
        }
        List<AppointmentDto> all = new ArrayList<>();
        for (Appointment appointment: appointments) {
            all.add(this.entityToDto(appointment));
        }
        return all;
    }

}
