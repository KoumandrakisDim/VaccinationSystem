package com.Vaccination.Repository;

import com.Vaccination.Models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query(value = "SELECT * FROM doctors WHERE id = ?",
            nativeQuery = true)
    Doctor findByIdCustom(Long id);
}
