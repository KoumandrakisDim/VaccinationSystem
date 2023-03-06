package com.Vaccination.Models.DTO;

import com.Vaccination.Models.Citizen;
import com.Vaccination.Models.Doctor;
import com.Vaccination.Models.Vaccination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationDTO {

    private Long id;

    private DoctorDTO doctor;

    private String date;

    private String expirationDate;

    private Long citizenId;

    public VaccinationDTO(Vaccination vac) {
        id=vac.getId();
        doctor=new DoctorDTO(vac.getDoctor());
        citizenId=vac.getCitizenId();
        date=vac.getDate();
        expirationDate=vac.getExpirationDate();
    }
}
