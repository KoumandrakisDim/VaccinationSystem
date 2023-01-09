package com.Vaccination.Models;

import com.Vaccination.Models.DTO.TimeslotDTO;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "timeslots")
public class Timeslot {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private int year;
    private int month;
    private int day;
    private int startingHour;
    private int startingMinute;
    private int finishHour;
    private int finishMinute;
    private String date;

    private Boolean isAvailable;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    public Timeslot(int year, int month, int day, int starting_hour,
                    int starting_minute, int finish_hour, int finish_minute, long doctor_id, String date) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.startingHour = starting_hour;
    this.startingMinute = starting_minute;
    this.finishHour = finish_hour;
    this.finishMinute = finish_minute;
    this.date = date;
    }

    public Timeslot(Long id, int year, int month, int day, int starting_hour, int starting_minute, int finish_hour, int finish_minute, long doctor_id) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.startingHour = starting_hour;
        this.startingMinute = starting_minute;
        this.finishHour = finish_hour;
        this.finishMinute = finish_minute;
    }

    public Timeslot(TimeslotDTO timeslot) {
        this.id = timeslot.getId();
        this.year = timeslot.getYear();
        this.month = timeslot.getMonth();
        this.day = timeslot.getDay();
        this.startingHour = timeslot.getStartingHour();
        this.startingMinute = timeslot.getStartingMinute();
        this.finishHour = timeslot.getFinishHour();
        this.finishMinute = timeslot.getFinishMinute();
        this.doctor = new Doctor(timeslot.getDoctor());
        this.isAvailable = timeslot.getIsAvailable();
        this.date = timeslot.getDate();
    }
}
