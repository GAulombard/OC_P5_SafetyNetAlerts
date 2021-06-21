package com.safetynetalerts.microservice.model.DTO;

/**
 * details for PersonInfoMedicalDTO
 */
public class PersonInfoMedicalDTO {
    /**
     * first name
     */
    private String firstName;
    /**
     * last name
     */
    private String lastName;
    /**
     * phone
     */
    private String phone;
    /**
     * age
     */
    private int age;
    /**
     * medical background
     *
     * @see MedicalBackgroundDTO
     */
    private MedicalBackgroundDTO medicalBackgroundDTO;

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

    /**
     * getter
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * setter
     *
     * @param phone phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * getter
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * setter
     *
     * @param age age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * getter
     *
     * @return medical background
     */
    public MedicalBackgroundDTO getMedicalBackgroundDTO() {
        return medicalBackgroundDTO;
    }

    /**
     * setter
     *
     * @param medicalBackgroundDTO medical background
     */
    public void setMedicalBackgroundDTO(MedicalBackgroundDTO medicalBackgroundDTO) {
        this.medicalBackgroundDTO = medicalBackgroundDTO;
    }
}
