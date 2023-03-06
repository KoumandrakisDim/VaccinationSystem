package com.Vaccination.Service;

import com.Vaccination.Models.Citizen;
import com.Vaccination.Models.DTO.CitizenDTO;
import com.Vaccination.Models.DTO.DoctorDTO;
import com.Vaccination.Models.Doctor;
import com.Vaccination.Repository.CitizenRepository;
import com.Vaccination.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.Vaccination.Service.Database.connectToDatabase;
import static com.Vaccination.Service.Database.getQueryResults;

@Service
public class CitizenService {

    @Autowired
    CitizenRepository citizenRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    public CitizenDTO getCitizenById(Long id) {
        return new CitizenDTO(citizenRepository.findByIdCustom(id));
    }

    public Object login(String amka, String password) {
        Citizen citizen = citizenRepository.findByAmka(amka);
        Doctor doctor = doctorRepository.findByAmka(amka);
        Citizen dummyCit = new Citizen();

        if(citizen != null){
            if(Objects.equals(citizen.getPassword(), password)){
                return citizen;
            }
            else{
                dummyCit.setAmka("Wrong password");
                return dummyCit;
            }
        }
        if(doctor != null){
            if(doctor.getPassword().equals(password)){
                return new DoctorDTO(doctor);
            }
            else{
                dummyCit.setAmka("Wrong password");
                return dummyCit;
            }
        }

        dummyCit.setAmka("Wrong amka");
        return dummyCit;
    }

    public List<Citizen> getAll() throws Exception {
//        ArrayList<Citizen> cList = new ArrayList<Citizen>();
//        String query = "SELECT * FROM citizens";
//
//        ResultSet rs = getQueryResults(query);
//
//        while (rs.next())
//        {
//            String name = rs.getString("name");
//            cList.add(new Citizen(name));
//        }
//        return cList;
        return citizenRepository.findAll();
    }

    public Citizen save(Citizen citizen) {

//        PreparedStatement preparedStatement;
        if(citizen.getId() != null){
            citizenRepository.save(citizen);

//
        }
//        preparedStatement.executeUpdate();

        return citizen;
    }

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        citizenRepository.deleteById(id);
    }



}
