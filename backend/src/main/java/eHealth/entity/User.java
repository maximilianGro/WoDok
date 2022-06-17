package eHealth.entity;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    @Value("$lockedCounter:0")
    private int lockedCounter;

    @Column(nullable = false)
    private Boolean doctor;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String city;

    @Column
    private String zip;

    @Column
    private String country;

    @Column
    private String street;

    @Column
    private String birthday;

    // Appointments für Patienten
    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "patient")
    private List<Appointment> patientAppointments = new ArrayList<>();

    // Appointments für Ärzte
    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "practitioner")
    private List<Appointment> practitionerAppointments = new ArrayList<>();

    public User(String email, String password, int lockedCounter, Boolean doctor, String firstName, String lastName, String city, String zip, String country, String street, String birthday) {
        this.email = email;
        this.password = password;
        this.lockedCounter = lockedCounter;
        this.doctor = doctor;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.zip = zip;
        this.country = country;
        this.street = street;
        this.birthday = birthday;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLockedCounter() {
        return lockedCounter;
    }

    public void setLockedCounter(int lockedCounter) {
        this.lockedCounter = lockedCounter;
    }

    public Boolean getDoctor() {
        return doctor;
    }

    public void setDoctor(Boolean doctor) {
        this.doctor = doctor;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public List<Appointment> getPatientAppointments() {
        return patientAppointments;
    }

    public void setPatientAppointments(List<Appointment> patientAppointments) {
        this.patientAppointments = patientAppointments;
    }

    public List<Appointment> getPractitionerAppointments() {
        return practitionerAppointments;
    }

    public void setPractitionerAppointments(List<Appointment> practitionerAppointments) {
        this.practitionerAppointments = practitionerAppointments;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", lockedCounter=" + lockedCounter +
                ", doctor=" + doctor +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", street='" + street + '\'' +
                ", birthday='" + birthday + '\'' +
                ", patientAppointments=" + patientAppointments +
                ", practitionerAppointments=" + practitionerAppointments +
                '}';
    }
}
