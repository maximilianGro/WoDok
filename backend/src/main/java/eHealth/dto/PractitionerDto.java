package eHealth.dto;

/**
 * Class for Practitioner DTOs
 * Contains all common properties
 */
public record PractitionerDto(Long id, String firstName, String lastName, String specialty, String openingHours, String address, String phone, String email) {

}