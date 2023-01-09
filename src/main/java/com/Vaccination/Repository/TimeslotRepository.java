package com.Vaccination.Repository;

import com.Vaccination.Models.Doctor;
import com.Vaccination.Models.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public interface TimeslotRepository extends JpaRepository<Timeslot, Long> {
    @Query(value = "SELECT * FROM timeslots WHERE month = ? AND is_available = 1",
            nativeQuery = true)
    ArrayList<Timeslot> findAllAvailableTimeslotsByMonth(int month);
    ArrayList<Timeslot> findAllByDate(String date);

    @Query(value = "SELECT * FROM timeslots WHERE id = ?",
            nativeQuery = true)
    Timeslot findByIdCustom(Long id);

    @Query(value = "SELECT * FROM timeslots WHERE doctor_id = ? AND is_available = 1",
            nativeQuery = true)
    ArrayList<Timeslot> findAllByDoctor(Long doctorId);
}
