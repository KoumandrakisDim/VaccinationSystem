package com.Vaccination.Models.DTO;

import com.Vaccination.Models.Doctor;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DoctorDTO {

    private Long id;
    private String amka;
    private String name;
    private String surname;

    public DoctorDTO(Doctor doctor){
        this.id = doctor.getId();
        this.amka = doctor.getAmka();
        this.name = doctor.getName();
        this.surname = doctor.getSurname();
    }
}
