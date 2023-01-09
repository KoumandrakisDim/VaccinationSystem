package com.Vaccination.Repository;

import com.Vaccination.Models.Appointment;
import com.Vaccination.helper.PaginationCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorId(Long id, Pageable criteria);

    Appointment findByCitizenId(Long id);


}
