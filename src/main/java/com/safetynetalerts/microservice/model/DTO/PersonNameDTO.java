package com.safetynetalerts.microservice.model.DTO;

/**
 * details for PersonNameDTO
 */
public class PersonNameDTO {
    /**
     * first name
     */
    private String firstName;
    /**
     * last name
     */
    private String lastName;

    /**
     * getter
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * setter
     *
     * @param firstName first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * getter
     *
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * setter
     *
     * @param lastName last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
