package com.Vaccination.Models;

import com.Vaccination.Models.DTO.VaccinationDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vaccination")
public class Vaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private Long citizenId;
    private String date;

    private String expirationDate;


    public Vaccination(VaccinationDTO vaccinationDTO) {
        doctor = new Doctor(vaccinationDTO.getDoctor());
        citizenId = vaccinationDTO.getCitizenId();
        date = vaccinationDTO.getDate();
        expirationDate = vaccinationDTO.getExpirationDate();
    }
}
