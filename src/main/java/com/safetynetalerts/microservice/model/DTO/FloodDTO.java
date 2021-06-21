package com.safetynetalerts.microservice.model.DTO;

import java.util.Set;

/**
 * details for FloodDTO
 */
public class FloodDTO {

    /**
     * address
     */
    private String address;
    /**
     * person info medical DTO
     *
     * @see PersonInfoMedicalDTO
     */
    private Set<PersonInfoMedicalDTO> personInfoMedical;

    /**
     * getter
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * setter
     *
     * @param address address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * getter
     *
     * @return person info medical
     */
    public Set<PersonInfoMedicalDTO> getPersonInfoMedical() {
        return personInfoMedical;
    }

    /**
     * setter
     *
     * @param personInfoMedical person info medical
     */
    public void setPersonInfoMedical(Set<PersonInfoMedicalDTO> personInfoMedical) {
        this.personInfoMedical = personInfoMedical;
    }
}
