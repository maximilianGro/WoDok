package eHealth.Repository;

import eHealth.entity.Appointment;
import eHealth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findAppointmentById(Long id);

    @Query("SELECT a From Appointment a where a.patient.id = :patientId")
    List<Appointment> findAllByPatientId(@Param("patientId") Long patientId);

    @Query("SELECT appointment FROM Appointment appointment where appointment.practitioner.id = :id and appointment.freeAppointment = TRUE")
    List<Appointment> findFreeByPractitionerId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Appointment a SET a.freeAppointment = FALSE, a.patient = :patient, a.patientDescription = :patientDescription WHERE a.id = :id")
    int bookAppointment(@Param("id") Long id, @Param("patient") User patient, @Param("patientDescription") String patientDescription);

}
