package com.Vaccination.Service;

import com.Vaccination.Models.*;
import com.Vaccination.Models.DTO.AppointmentDTO;
import com.Vaccination.Repository.*;
import com.Vaccination.helper.PaginationCriteria;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentRepositoryCustom appointmentRepositoryCustom;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    EntityManager entityManager;
    @Autowired
    private TimeslotRepository timeslotRepository;
    @Autowired
    private CitizenRepository citizenRepository;

    @Override
    public AppointmentDTO save(AppointmentDTO appointmentDTO) {

        Citizen citizen = citizenRepository.findByIdCustom(appointmentDTO.getCitizen().getId());
        Doctor doctor = doctorRepository.findByIdCustom(appointmentDTO.getDoctor().getId());
        Timeslot timeslot = timeslotRepository.findByIdCustom(appointmentDTO.getTimeslot().getId());
        citizen.setTimesCanceled(citizen.getTimesCanceled() + 1);
        timeslot.setIsAvailable(Boolean.FALSE);
        timeslotRepository.save(timeslot);
        citizenRepository.save(citizen);

        Appointment appointment = appointmentRepository.save(new Appointment(timeslot,citizen,doctor));

        return new AppointmentDTO(appointment);
    }

    public void delete(Long appointmentId, Long timeslotId){
        Timeslot timeslot = timeslotRepository.findByIdCustom(timeslotId);
        timeslot.setIsAvailable(true);
        timeslotRepository.save(timeslot);
        appointmentRepository.deleteById(appointmentId);
    }

    @Override
    public ArrayList<AppointmentDTO> getAppointmentsForDoctor(Long doctorId, int pageSize ,int pageNumber){
        ArrayList<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        Pageable pageCriteria = PageRequest.of(pageNumber, pageSize);

       List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId, pageCriteria);
       for(Appointment ap:appointments){
           appointmentDTOS.add(new AppointmentDTO(ap));
       }

       return appointmentDTOS;
    }

    @Override
    public List<AppointmentDTO> findByDoctorIdAndDate(Long id, String date, PaginationCriteria criteria) {
        ArrayList<AppointmentDTO> appointmentDTOS = new ArrayList<>();

        Pageable pageCriteria = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
        List<Appointment> appointments =
                appointmentRepositoryCustom.findAllByDoctorIdAndDate(id, date, pageCriteria);

        for(Appointment ap:appointments){
            appointmentDTOS.add(new AppointmentDTO(ap));
        }

        return appointmentDTOS;
    }

    @Override
    public AppointmentDTO getAppointmentsForCitizen(Long citizenId) {
        if(appointmentRepository.findByCitizenId(citizenId) == null){
            return null;
        }
        return new AppointmentDTO(appointmentRepository.findByCitizenId(citizenId));
    }

//    public ArrayList<Appointment> getAppointmentsByDoctorId(Long doctorId) throws SQLException, ClassNotFoundException {
//        PreparedStatement preparedStatement = connectToDatabase().prepareStatement("SELECT * FROM" +
//                " appointments WHERE doctor_id = ?");
//        preparedStatement.setLong(1, doctorId);
//        ResultSet result = preparedStatement.executeQuery();
//        ArrayList<Appointment> array = new ArrayList<>();
//        while(result.next()){
//            new Appointment(result.getObject());
//        }
//        return array;
//    }

//    public List<Appointment> getAppointmentsForDoctor(Doctor doctor) {
//        return appointmentRepository.findByDoctor(doctor);
//    }
}
