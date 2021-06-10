package com.safetynetalerts.microservice.model.DTO;

import java.util.Set;

public class FloodDTO {

    private String address;
    private Set<PersonInfoMedicalDTO> personInfoMedical;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<PersonInfoMedicalDTO> getPersonInfoMedical() {
        return personInfoMedical;
    }

    public void setPersonInfoMedical(Set<PersonInfoMedicalDTO> personInfoMedical) {
        this.personInfoMedical = personInfoMedical;
    }
}
