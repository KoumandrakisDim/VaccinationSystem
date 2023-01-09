package com.Vaccination.Models.DTO;

import com.Vaccination.Models.Citizen;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CitizenDTO {

    private Long id;
    private String amka;
    private String afm;
    private String name;
    private String surname;
    private String email;
    private int timesCanceled;

    public CitizenDTO(Citizen citizen){
        this.id = citizen.getId();
        this.amka = citizen.getAmka();
        this.afm = citizen.getAfm();
        this.name = citizen.getName();
        this.surname = citizen.getSurname();
        this.email = citizen.getEmail();
        this.timesCanceled = citizen.getTimesCanceled();
    }
}
