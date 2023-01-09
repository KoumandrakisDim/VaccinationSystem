package com.Vaccination.Service;

import com.Vaccination.Models.Doctor;
import com.Vaccination.Repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import static com.Vaccination.Service.Database.connectToDatabase;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
//    private final DoctorRepository doctorRepository;

//    public DoctorService(DoctorRepository doctorRepository) {
//        this.doctorRepository = doctorRepository;
//    }

    public Doctor save(Doctor doctor) throws SQLException, ClassNotFoundException {

        PreparedStatement preparedStatement;
        if(doctor.getId() != null){
            preparedStatement = connectToDatabase().prepareStatement("UPDATE doctors " +
                    "SET amka = ?, name = ?, surname = ? WHERE id = ?");

            preparedStatement.setString(1, doctor.getAmka());
            preparedStatement.setString(2, doctor.getName());
            preparedStatement.setString(3, doctor.getSurname());
            preparedStatement.setLong(4, doctor.getId());
        }
        else{
            preparedStatement = connectToDatabase().prepareStatement("INSERT INTO doctors" +
                    " (amka, name, surname) VALUES(?, ?, ?)");

            preparedStatement.setString(1, doctor.getAmka());
            preparedStatement.setString(2, doctor.getName());
            preparedStatement.setString(3, doctor.getSurname());

        }
        preparedStatement.executeUpdate();

        return doctor;
    }

    public Optional<Doctor> findDoctorById(Long id) throws SQLException, ClassNotFoundException {
//        Connection conn = connectToDatabase();
//        PreparedStatement preparedStatement = connectToDatabase().prepareStatement("SELECT * FROM" +
//                " doctors WHERE id = ?");
//        preparedStatement.setLong(1, id);
//        ResultSet result = preparedStatement.executeQuery();
//        Doctor doctor = new Doctor(result.getLong("id"), result.getString("amka"),
//                result.getString("name"), result.getString("surname"));

        return doctorRepository.findById(id);
    }

//    public Optional<Doctor> findDoctorByIdORM(Long id) throws SQLException, ClassNotFoundException {
//        return doctorRepository.findById(id);
//    }


}
