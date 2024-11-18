package com.jsf.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.jsf.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	List<Appointment> findByEmailOrderByDateAsc(String email);
    // Custom query to find the doctor's name by email
    @Query("SELECT a.doctor FROM Appointment a WHERE a.email = ?1")
    String findDoctorByEmail(String email);

    // Get appointments for a doctor where the date is after today, sorted by date (ascending)
    List<Appointment> findByDoctorAndDateAfterOrderByDateAsc(String doctor, LocalDate date);
    
    // Get appointments for a doctor where the date is before today, sorted by date (descending)
    List<Appointment> findByDoctorAndDateBeforeOrderByDateDesc(String doctor, LocalDate date);
}
