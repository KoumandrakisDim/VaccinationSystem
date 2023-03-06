package com.Vaccination.Repository;

import com.Vaccination.Models.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {
}
