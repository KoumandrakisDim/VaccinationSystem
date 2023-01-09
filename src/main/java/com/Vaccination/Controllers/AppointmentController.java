package com.Vaccination.Controllers;

import com.Vaccination.Models.DTO.AppointmentDTO;
import com.Vaccination.Repository.CitizenRepository;
import com.Vaccination.Repository.TimeslotRepository;
import com.Vaccination.Service.AppointmentService;
import com.Vaccination.Service.AppointmentServiceImpl;
import com.Vaccination.Service.DoctorService;
import com.Vaccination.helper.PaginationCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AppointmentController {

    @Autowired
    private AppointmentServiceImpl appointmentServiceImpl;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private CitizenRepository citizenRepository;
    @Autowired
    private TimeslotRepository timeslotRepository;

    @PostMapping("/appointments/createAppointment")
    public AppointmentDTO createAppointment(@RequestBody AppointmentDTO appointment) {
        return appointmentService.save(appointment);
    }

    @GetMapping("/appointments/doctor")
    public ArrayList<AppointmentDTO> getAppointmentsForDoctor(@RequestParam Long doctorId,
                                                              @RequestParam int pageSize,
                                                              @RequestParam int pageNumber) {
        return appointmentService.getAppointmentsForDoctor(doctorId, pageSize, pageNumber);
    }

    @GetMapping("/appointments/doctorDate")
    public List<AppointmentDTO> getAppointmentsForDoctorByDate(@RequestParam Long doctorId,
                                                               @RequestParam String date,
                                                               @RequestBody PaginationCriteria criteria) {
        return appointmentService.findByDoctorIdAndDate(doctorId, date, criteria);
    }

    @GetMapping("/appointments/citizen")
    public AppointmentDTO getAppointmentForCitizen(@RequestParam Long citizenId) {
        return appointmentService.getAppointmentsForCitizen(citizenId);
    }

    @DeleteMapping("/appointments/cancelAppointment")
    public void cancelAppointment(@RequestParam Long appointmentId, @RequestParam Long timeslotId) {
        appointmentService.delete(appointmentId, timeslotId);
    }
}
