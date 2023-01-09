package com.Vaccination.Service;

import com.Vaccination.Models.Citizen;
import com.Vaccination.Models.DTO.CitizenDTO;
import com.Vaccination.Repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.Vaccination.Service.Database.connectToDatabase;
import static com.Vaccination.Service.Database.getQueryResults;

@Service
public class CitizenService {

    @Autowired
    CitizenRepository citizenRepository;
    public CitizenDTO getCitizenById(Long id) {
        return new CitizenDTO(citizenRepository.findByIdCustom(id));
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

    public Citizen save(Citizen citizen) throws SQLException, ClassNotFoundException {

//        PreparedStatement preparedStatement;
        if(citizen.getId() != null){
            citizenRepository.save(citizen);

//            preparedStatement = connectToDatabase().prepareStatement("UPDATE citizens " +
//                    "SET amka = ?, afm = ?, name = ?, surname = ?, email = ? WHERE id = ?");
//
//            preparedStatement.setString(1, citizen.getAmka());
//            preparedStatement.setString(2, citizen.getAfm());
//            preparedStatement.setString(3, citizen.getName());
//            preparedStatement.setString(4, citizen.getSurname());
//            preparedStatement.setString(5, citizen.getEmail());
//            preparedStatement.setLong(6, citizen.getId());
        }
        else{
//            preparedStatement = connectToDatabase().prepareStatement("INSERT INTO citizens" +
//                    " (amka, afm, name, surname, email) VALUES(?, ?, ?, ?, ?)");
//            preparedStatement.setString(1, citizen.getAmka());
//            preparedStatement.setString(2, citizen.getAfm());
//            preparedStatement.setString(3, citizen.getName());
//            preparedStatement.setString(4, citizen.getSurname());
//            preparedStatement.setString(5, citizen.getEmail());
//
        }
//        preparedStatement.executeUpdate();

        return citizen;
    }

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        citizenRepository.deleteById(id);
    }



}
