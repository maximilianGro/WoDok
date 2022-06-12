package eHealth.dto;

import java.util.Objects;

/**
 * Class for Practitioner DTOs
 * Contains all common properties
 */
public class PractitionerDto {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String specialty;
    private final String openingHours;
    private final String address;
    private final String phone;
    private final String email;

    public PractitionerDto(Long id, String firstName, String lastName, String specialty, String openingHours, String address, String phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.openingHours = openingHours;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Long id() {
        return id;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public String specialty() {
        return specialty;
    }

    public String openingHours() {
        return openingHours;
    }

    public String address() {
        return address;
    }

    public String phone() {
        return phone;
    }

    public String email() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PractitionerDto) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName) &&
                Objects.equals(this.specialty, that.specialty) &&
                Objects.equals(this.openingHours, that.openingHours) &&
                Objects.equals(this.address, that.address) &&
                Objects.equals(this.phone, that.phone) &&
                Objects.equals(this.email, that.email);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, specialty, openingHours, address, phone, email);
    }

    @Override
    public String toString() {
        return "PractitionerDto[" +
                "id=" + id + ", " +
                "firstName=" + firstName + ", " +
                "lastName=" + lastName + ", " +
                "specialty=" + specialty + ", " +
                "openingHours=" + openingHours + ", " +
                "address=" + address + ", " +
                "phone=" + phone + ", " +
                "email=" + email + ']';
    }


}