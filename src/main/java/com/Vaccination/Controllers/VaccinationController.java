package com.Vaccination.Controllers;

import com.Vaccination.Models.DTO.VaccinationDTO;
import com.Vaccination.Models.Vaccination;
import com.Vaccination.Service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class VaccinationController {

    @Autowired
    private VaccinationService vaccinationService;

    @PostMapping("/vaccination/save")
    public VaccinationDTO confirmVaccination(@RequestBody VaccinationDTO vaccination){
        Vaccination vac = vaccinationService.save(vaccination, vaccination.getCitizenId());
        return new VaccinationDTO(vac);
    }

}
