package com.safetynetalerts.microservice.model.DTO;

import java.util.List;


public class MedicalBackgroundDTO {

    private List<String> medicationsList;
    private List<String> allergiesList;

    public List<String> getMedicationsList() {
        return medicationsList;
    }

    public void setMedicationsList(List<String> medicationsList) {
        this.medicationsList = medicationsList;
    }

    public List<String> getAllergiesList() {
        return allergiesList;
    }

    public void setAllergiesList(List<String> allergiesList) {
        this.allergiesList = allergiesList;
    }
}
