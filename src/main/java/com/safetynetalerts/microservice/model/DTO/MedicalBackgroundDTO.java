package com.safetynetalerts.microservice.model.DTO;

import java.util.List;

/**
 * details for MedicalBackgroundDTO
 */
public class MedicalBackgroundDTO {
    /**
     * medications list
     */
    private List<String> medicationsList;
    /**
     * allergies list
     */
    private List<String> allergiesList;

    /**
     * getter
     *
     * @return medications list
     */
    public List<String> getMedicationsList() {
        return medicationsList;
    }

    /**
     * setter
     *
     * @param medicationsList medications list
     */
    public void setMedicationsList(List<String> medicationsList) {
        this.medicationsList = medicationsList;
    }

    /**
     * getter
     *
     * @return allergies list
     */
    public List<String> getAllergiesList() {
        return allergiesList;
    }

    /**
     * setter
     *
     * @param allergiesList allergies list
     */
    public void setAllergiesList(List<String> allergiesList) {
        this.allergiesList = allergiesList;
    }
}
