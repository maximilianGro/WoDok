package eHealth.dto;


import java.time.LocalDateTime;

public class AppointmentDto {
    private Long id;
    private Boolean freeAppointment;
    private Long practitionerId;
    private Long patientId;
    private LocalDateTime start;
    private LocalDateTime end;
    private String patientDescription;

    public AppointmentDto() {
    }

    public AppointmentDto(Long id, Boolean freeAppointment, Long practitionerId, Long patientId, LocalDateTime start, LocalDateTime end, String patientDescription) {
        this.id = id;
        this.freeAppointment = freeAppointment;
        this.practitionerId = practitionerId;
        this.patientId = patientId;
        this.start = start;
        this.end = end;
        this.patientDescription = patientDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFreeAppointment() {
        return freeAppointment;
    }

    public void setFreeAppointment(Boolean freeAppointment) {
        this.freeAppointment = freeAppointment;
    }

    public Long getPractitionerId() {
        return practitionerId;
    }

    public void setPractitionerId(Long practitionerId) {
        this.practitionerId = practitionerId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getPatientDescription() {
        return patientDescription;
    }

    public void setPatientDescription(String patientDescription) {
        this.patientDescription = patientDescription;
    }
}
