package com.Vaccination.Models;

import com.Vaccination.Models.DTO.DoctorDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctors")

public class Doctor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String amka;
    private String name;
    private String surname;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Appointment> appointments;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Timeslot> timeslots;

    public Doctor(DoctorDTO doctor) {
        this.id = doctor.getId();
        this.amka = doctor.getAmka();
        this.name = doctor.getName();
        this.surname = doctor.getSurname();
    }
}
