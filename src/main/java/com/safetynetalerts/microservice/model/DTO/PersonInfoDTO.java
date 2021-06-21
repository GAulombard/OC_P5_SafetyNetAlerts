package com.safetynetalerts.microservice.model.DTO;


public class PersonInfoDTO {
    /**
     * first name
     */
    private String firstName;
    /**
     * last name
     */
    private String lastName;
    /**
     * phone number
     */
    private String phone;
    /**
     * address
     */
    private String address;
    /**
     * city
     */
    private String email;

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
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter
     *
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * define toString() method
     *
     * @return
     */
    @Override
    public String toString() {
        return "" + firstName + " " + lastName + " " + address + " " + phone + " " + email + "\n";
    }
}
