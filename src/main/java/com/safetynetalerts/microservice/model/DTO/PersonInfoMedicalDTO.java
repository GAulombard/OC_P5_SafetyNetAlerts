package com.safetynetalerts.microservice.model.DTO;

public class PersonInfoMedicalDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private MedicalBackgroundDTO medicalBackgroundDTO;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public MedicalBackgroundDTO getMedicalBackgroundDTO() {
        return medicalBackgroundDTO;
    }

    public void setMedicalBackgroundDTO(MedicalBackgroundDTO medicalBackgroundDTO) {
        this.medicalBackgroundDTO = medicalBackgroundDTO;
    }
}
