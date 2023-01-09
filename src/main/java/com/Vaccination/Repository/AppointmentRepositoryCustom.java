package com.Vaccination.Repository;

import com.Vaccination.Models.Appointment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AppointmentRepositoryCustom extends PagingAndSortingRepository<Appointment, Long> {

    @Query(value = "SELECT * FROM appointments WHERE doctor_id = ? AND date = ?",
            nativeQuery = true)
    List<Appointment> findAllByDoctorIdAndDate(Long id, String date, Pageable pageable);
}
