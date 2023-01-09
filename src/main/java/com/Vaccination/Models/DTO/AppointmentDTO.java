package com.Vaccination.Models.DTO;

import com.Vaccination.Models.Appointment;
import com.Vaccination.Models.Timeslot;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppointmentDTO {

    private Long id;
    private TimeslotDTO timeslot;
    private CitizenDTO citizen;
    private DoctorDTO doctor;
    private String date;
    private Long timeslotId;
    private Long citizenId;
    private Long doctorId;


    public AppointmentDTO(Long id, Timeslot timeslot, Appointment appointment, Long citizenId ){
        this.id = id;
        this.timeslot = new TimeslotDTO(appointment.getTimeslot());
        this.citizenId = citizenId;
        this.doctor = new DoctorDTO(appointment.getDoctor());
        this.date = appointment.getDate();
    }

    public AppointmentDTO(Appointment appointment) {
        this.id = appointment.getId();
        this.timeslot = new TimeslotDTO(appointment.getTimeslot());
        this.citizen = new CitizenDTO(appointment.getCitizen());
        this.doctor = new DoctorDTO(appointment.getDoctor());
        this.date = appointment.getDate();
    }
}
