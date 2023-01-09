package com.Vaccination.Controllers;

//import com.Vaccination.Repository.TimeslotCriteria;
import com.Vaccination.Models.DTO.TimeslotDTO;
import com.Vaccination.Service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    @GetMapping(path="/timeslot/searchByMonth")
    public ArrayList<TimeslotDTO> findAllAvailableTimeslotsByMonth(@RequestParam int month) {
        return timeSlotService.getTimeslotsByMonth(month);
    }

    @GetMapping(path="/timeslot/searchByDate")
    public List<TimeslotDTO> searchByDate(@RequestParam String date) {
        return timeSlotService.getTimeslotsByDate(date);
    }

    @GetMapping(path="/timeslot/bookTimeslot")
    public String bookTimeslot(@RequestParam Long timeslotId, @RequestParam Long citizenId) throws SQLException, ClassNotFoundException {
        return timeSlotService.bookTimeslot(timeslotId, citizenId);
    }

    @GetMapping(path="/timeslot/getByDoctorId")
    public ArrayList<TimeslotDTO> bookTimeslot(@RequestParam Long doctorId)  {
        return timeSlotService.getByDoctorId(doctorId);
    }

    @PostMapping(path="/timeslot/create")
    public TimeslotDTO createTimeslot(@RequestBody TimeslotDTO timeslot)  {
        return timeSlotService.save(timeslot);
    }

    @DeleteMapping(path="/timeslot/cancelTimeslot")
    public void cancelTimeslot(@RequestParam Long timeslotId)  {
        timeSlotService.cancelTimeslotBooking(timeslotId);
    }

//    @GetMapping(path="/timeslot/cancelTimeslotBooking")
//    public String cancelTimeslotBooking(@RequestParam Long timeslotId, @RequestParam Long citizenId) throws SQLException, ClassNotFoundException {
//        return timeSlotService.cancelTimeslotBooking(timeslotId, citizenId);
//    }
}
