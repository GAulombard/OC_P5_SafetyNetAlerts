package com.safetynetalerts.microservice.model.DTO;

public class PersonFullInfoDTO {

    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private String phone;
    private String email;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MedicalBackgroundDTO getMedicalBackgroundDTO() {
        return medicalBackgroundDTO;
    }

    public void setMedicalBackgroundDTO(MedicalBackgroundDTO medicalBackgroundDTO) {
        this.medicalBackgroundDTO = medicalBackgroundDTO;
    }
}