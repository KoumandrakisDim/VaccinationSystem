package com.Vaccination.Models;

import com.Vaccination.Models.DTO.CitizenDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "citizens")
public class Citizen {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String amka;
    private String afm;
    private String name;
    private String surname;
    private String email;
    private int timesCanceled;
    private String password;
    private Boolean isVaccinated;
    private String vaccinationExpirationDate;

    public Citizen(CitizenDTO citizenDTO) {
        this.name = citizenDTO.getName();
        this.amka = citizenDTO.getAmka();
        this.afm = citizenDTO.getAfm();
        this.name = citizenDTO.getName();
        this.surname = citizenDTO.getSurname();
        this.email = citizenDTO.getEmail();
        this.timesCanceled = citizenDTO.getTimesCanceled();
        this.isVaccinated = citizenDTO.getIsVaccinated();
        this.vaccinationExpirationDate = citizenDTO.getVaccinationExpirationDate();
    }

}
