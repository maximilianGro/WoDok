package eHealth.dto;


import java.time.LocalDateTime;

public class AppointmentDto {
    UserRegisterDto practitioner;

    UserRegisterDto patient;

    LocalDateTime date;

    public UserRegisterDto getPractitionerId() {
        return practitioner;
    }

    public void setPractitioner(UserRegisterDto practitioner) {
        this.practitioner = practitioner;
    }

    public UserRegisterDto getPatientId() {
        return patient;
    }

    public void setPatient(UserRegisterDto patient) {
        this.patient = patient;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AppointmentDto{" +
                "practitionerId=" + practitioner +
                ", patientId=" + patient +
                ", date=" + date +
                '}';
    }
}
