package com.Vaccination.Repository;

import com.Vaccination.Models.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {

    @Query(value = "SELECT * FROM citizens WHERE id = ?",
            nativeQuery = true)
    Citizen findByIdCustom(Long id);

    Citizen findByAmka(String amka);
}
