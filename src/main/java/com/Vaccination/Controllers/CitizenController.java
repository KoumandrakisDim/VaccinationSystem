package com.Vaccination.Controllers;

import com.Vaccination.Models.Citizen;
import com.Vaccination.Models.DTO.CitizenDTO;
import com.Vaccination.Service.CitizenService;
import com.Vaccination.helper.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

    @GetMapping(path="login")
    public Object login(@RequestParam String amka, @RequestParam String password) {
        return citizenService.login(amka, password);

    }

    @GetMapping(path="citizen/getById")
    public CitizenDTO getBookById(@RequestParam Long id) throws SQLException, ClassNotFoundException {
//        return citizenService.getCitizenById(id).orElseThrow(() -> new CitizenNotFoundException(id));
        return citizenService.getCitizenById(id);

    }

    @GetMapping(path="citizen/getAll")
    public List<Citizen> getAll() throws Exception {
        return citizenService.getAll();
    }

    @PostMapping(path="citizen/create")
    public Citizen create(@RequestBody Citizen citizen) throws SQLException, ClassNotFoundException {
        citizen = citizen;
        return citizenService.save(citizen);
    }

    @PostMapping(path="citizen/update")
    public Citizen update(@RequestBody Citizen citizen) throws SQLException, ClassNotFoundException {
        return citizenService.save(citizen);
    }

    @PostMapping(path="citizen/delete")
    public void delete(@RequestParam Long id) throws SQLException, ClassNotFoundException {
        citizenService.delete(id);
    }
}
