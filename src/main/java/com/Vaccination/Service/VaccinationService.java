package com.Vaccination.Service;

import com.Vaccination.Models.Citizen;
import com.Vaccination.Models.DTO.CitizenDTO;
import com.Vaccination.Models.DTO.DoctorDTO;
import com.Vaccination.Models.DTO.VaccinationDTO;
import com.Vaccination.Models.Doctor;
import com.Vaccination.Models.Vaccination;
import com.Vaccination.Repository.CitizenRepository;
import com.Vaccination.Repository.DoctorRepository;
import com.Vaccination.Repository.VaccinationRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;

@Service
public class VaccinationService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    VaccinationRepository vaccinationRepository;

    @Autowired
    CitizenRepository citizenRepository;
    @Autowired
    CitizenService citizenService;
    @Autowired
    private DoctorRepository doctorRepository;

    public Vaccination save(VaccinationDTO vaccination, Long citizenId) {
        Doctor doctor = doctorRepository.findByIdCustom(vaccination.getDoctor().getId());
        Citizen citizen = citizenRepository.findByIdCustom(citizenId);

        String expirationDate = String.valueOf(LocalDate.parse(vaccination.getDate()).plusMonths(6));
        citizen.setIsVaccinated(true);
        citizen.setVaccinationExpirationDate(expirationDate);
        citizenService.save(citizen);

        vaccination.setDoctor(new DoctorDTO(doctor));
        vaccination.setExpirationDate(expirationDate);

        return vaccinationRepository.save(new Vaccination(vaccination));
    }

}
