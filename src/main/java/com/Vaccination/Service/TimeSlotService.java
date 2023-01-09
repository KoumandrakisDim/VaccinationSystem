package com.Vaccination.Service;

import com.Vaccination.Models.Timeslot;
//import com.Vaccination.Repository.TimeslotCriteria;
import com.Vaccination.Models.DTO.TimeslotDTO;
import com.Vaccination.Repository.AppointmentRepository;
import com.Vaccination.Repository.TimeslotRepository;
//import com.Vaccination.Repository.TimeslotRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.Vaccination.Service.Database.connectToDatabase;

@Service
public class TimeSlotService {

    @Autowired
    private TimeslotRepository timeslotRepository;
    @Autowired
    private AppointmentServiceImpl appointmentServiceImpl;

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public TimeslotDTO save(TimeslotDTO timeslot){
        timeslot.setIsAvailable(true);
        return new TimeslotDTO(timeslotRepository.save(new Timeslot(timeslot)));
    }

    public Timeslot save1(Timeslot timeslot) throws SQLException, ClassNotFoundException {

        PreparedStatement preparedStatement;
        if(timeslot.getId() != null){
            preparedStatement = connectToDatabase().prepareStatement("UPDATE timeslots " +
                    "SET year = ?, month = ?, day = ?, starting_hour = ?, starting_minute = ?" +
                    "finish_hour = ?, finish_minute = ? WHERE id = ?");

            preparedStatement.setInt(1, timeslot.getYear());
            preparedStatement.setInt(2, timeslot.getMonth());
            preparedStatement.setInt(3, timeslot.getDay());
            preparedStatement.setInt(4, timeslot.getStartingHour());
            preparedStatement.setInt(5, timeslot.getStartingMinute());
            preparedStatement.setInt(6, timeslot.getFinishHour());
            preparedStatement.setInt(7, timeslot.getFinishMinute());

            preparedStatement.setLong(8, timeslot.getId());
        }
        else{
            preparedStatement = connectToDatabase().prepareStatement("INSERT INTO timeslots" +
                    " (year, month , day, starting_hour, starting_minute" +
                    " finish_hour , finish_minute) VALUES(?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, timeslot.getYear());
            preparedStatement.setInt(2, timeslot.getMonth());
            preparedStatement.setInt(3, timeslot.getDay());
            preparedStatement.setInt(4, timeslot.getStartingHour());
            preparedStatement.setInt(5, timeslot.getStartingMinute());
            preparedStatement.setInt(6, timeslot.getFinishHour());
            preparedStatement.setInt(7, timeslot.getFinishMinute());


        }
        preparedStatement.executeUpdate();

        return timeslot;
    }

//    public List<Timeslot> searchByYearMonthDay(TimeslotCriteria criteria) throws SQLException, ClassNotFoundException {
//        return timeslotRepositoryCustom.searchByYearMonthDay(criteria);
//    }

    public ArrayList<String> getTimeslotById(Long id) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = connectToDatabase().prepareStatement("SELECT * FROM" +
                " timeslots WHERE id = ?");
        preparedStatement.setLong(1, id);
        ResultSet result = preparedStatement.executeQuery();
        ArrayList<String> array = new ArrayList<>();
        while(result.next()){
            new Timeslot(result.getInt("year"), result.getInt("month"),
                    result.getInt("day"), result.getInt("starting_hour"),
                    result.getInt("starting_minute"), result.getInt("finish_hour"),
                    result.getInt("finish_minute"),
                    result.getLong("doctor_id"),
                    result.getString("date"));
        }
        return array;
    }

    public String bookTimeslot(Long timeslotId, Long citizenId) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = connectToDatabase().prepareStatement("UPDATE timeslots" +
                " SET is_available = ? WHERE id = ?");
        preparedStatement.setInt(1, 1);
        preparedStatement.setLong(2, timeslotId);
        preparedStatement.executeUpdate();
//        appointmentServiceImpl.save(new Appointment(timeslotId, citizenId));
        return "timeslot booked";
    }


    public void cancelTimeslotBooking(Long timeslotId)  {
        timeslotRepository.deleteById(timeslotId);
    }

    public ArrayList<TimeslotDTO> getTimeslotsByMonth(int month){
        List<Timeslot> timeslots = timeslotRepository.findAllAvailableTimeslotsByMonth(month);
        ArrayList<TimeslotDTO> timeslotDTOS = new ArrayList<>();
        for (Timeslot timeslot:timeslots){
            timeslotDTOS.add(new TimeslotDTO(timeslot));
        }
        return timeslotDTOS;
    }

    public List<TimeslotDTO> getTimeslotsByDate(String date) {
        List<Timeslot> timeslots = timeslotRepository.findAllByDate(date);
        ArrayList<TimeslotDTO> timeslotDTOS = new ArrayList<>();
        for (Timeslot timeslot:timeslots){
            timeslotDTOS.add(new TimeslotDTO(timeslot));
        }
        return timeslotDTOS;
    }

    public ArrayList<TimeslotDTO> getByDoctorId(Long doctorId) {
        ArrayList<TimeslotDTO> timeslots = new ArrayList<>();
        for(Timeslot timeslot:timeslotRepository.findAllByDoctor(doctorId)){
            timeslots.add(new TimeslotDTO(timeslot));
        }
        return timeslots;
    }
}
