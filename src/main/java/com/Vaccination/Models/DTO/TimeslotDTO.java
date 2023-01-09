package com.Vaccination.Models.DTO;

import com.Vaccination.Models.Timeslot;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TimeslotDTO {

    private Long id;
    private int year;
    private int month;
    private int day;
    private int startingHour;
    private int startingMinute;
    private int finishHour;
    private int finishMinute;

    private Boolean isAvailable;
    private String date;
    private DoctorDTO doctor;

    public TimeslotDTO(Timeslot timeslot){
        this.id = timeslot.getId();
        this.year = timeslot.getYear();
        this.month = timeslot.getMonth();
        this.day = timeslot.getDay();
        this.startingHour = timeslot.getStartingHour();
        this.startingMinute = timeslot.getStartingMinute();
        this.finishHour = timeslot.getFinishHour();
        this.finishMinute = timeslot.getFinishMinute();
        this.date = timeslot.getDate();
        this.doctor = new DoctorDTO(timeslot.getDoctor());
        this.isAvailable = timeslot.getIsAvailable();

    }
}
