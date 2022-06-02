package eHealth.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patientId")
    private User practitioner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "practitionerId")
    private User patient;

    //@Column(nullable = false)
    private LocalDateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getPractitioner() {
        return practitioner;
    }

    public void setPractitioner(User practitioner) {
        this.practitioner = practitioner;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
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
        return "Appointment{" +
                "id=" + id +
                ", practitioner=" + practitioner.getEmail() +
                ", patient=" + patient.getEmail() +
                ", date=" + date +
                '}';
    }
}
