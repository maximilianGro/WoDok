package eHealth.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "appointment_table")
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean freeAppointment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "practitionerId")
    private Practitioner practitioner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patientId")
    private User patient;

    @Column(nullable = false)
    private LocalDateTime start;

    @Column(nullable = false)
    private LocalDateTime end;

    @Column
    private String patientDescription;

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

    public Practitioner getPractitioner() {
        return practitioner;
    }

    public void setPractitioner(Practitioner practitioner) {
        this.practitioner = practitioner;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
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
