package com.safetynetalerts.microservice.model.DTO;

import java.util.Set;

/**
 * details for ChildAlertDTO
 */
public class ChildAlertDTO {
    /**
     * first name
     */
    private String firstName;
    /**
     * last name
     */
    private String lastName;
    /**
     * age
     */
    private int age;
    /**
     * other members
     *
     * @see PersonNameDTO
     */
    private Set<PersonNameDTO> otherMembers;

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
     * @return other members
     */
    public Set<PersonNameDTO> getOtherMembers() {
        return otherMembers;
    }

    /**
     * setter
     *
     * @param otherMembers other members
     */
    public void setOtherMembers(Set<PersonNameDTO> otherMembers) {
        this.otherMembers = otherMembers;
    }
}
