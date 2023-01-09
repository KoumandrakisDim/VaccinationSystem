package com.Vaccination.Service;

import com.Vaccination.Models.DTO.AppointmentDTO;
import com.Vaccination.helper.PaginationCriteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface AppointmentService {

    AppointmentDTO save(AppointmentDTO appointment);

    void delete(Long appointmentId, Long timeslotId );

    ArrayList<AppointmentDTO> getAppointmentsForDoctor(Long doctorId, int pageSize ,int pageNumber);

    List<AppointmentDTO> findByDoctorIdAndDate(Long id, String date, PaginationCriteria criteria);

    AppointmentDTO getAppointmentsForCitizen(Long citizenId);
}
