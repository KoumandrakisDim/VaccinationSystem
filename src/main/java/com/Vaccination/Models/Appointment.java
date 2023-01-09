package com.Vaccination.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "timeslot_id")
    private Timeslot timeslot;

    @ManyToOne
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private String date;

    public Appointment(Timeslot timeslot, Citizen citizen, Doctor doctor){
//        this.id = id;
        this.timeslot = timeslot;
        this.citizen = citizen;
        this.doctor = doctor;
        this.date = timeslot.getDate();
    }
//    public Appointment(Long timeslotId, Long citizenId){
//        this.timeslotId = timeslotId;
//        this.citizenId = citizenId;
//    }
}
