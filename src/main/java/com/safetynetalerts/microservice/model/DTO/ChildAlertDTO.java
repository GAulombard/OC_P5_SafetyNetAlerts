package com.safetynetalerts.microservice.model.DTO;

import java.util.Set;

public class ChildAlertDTO {

    private String firstName;
    private String lastName;
    private int age;
    private Set<PersonNameDTO> otherMembers;

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

    public Set<PersonNameDTO> getOtherMembers() {
        return otherMembers;
    }

    public void setOtherMembers(Set<PersonNameDTO> otherMembers) {
        this.otherMembers = otherMembers;
    }
}
