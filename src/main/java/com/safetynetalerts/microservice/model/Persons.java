package com.safetynetalerts.microservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * Persons details
 */
@JsonTypeName("persons")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Persons {
    /**
     * first name
     */
    @NotBlank(message = "First name needed")
    private String firstName;
    /**
     * last name
     */
    @NotBlank(message = "Last name needed")
    private String lastName;
    /**
     * phone number
     */
    @NotBlank(message = "phone needed")
    private String phone;
    /**
     * ZIP number
     */
    @NotBlank(message = "zip needed")
    @Positive(message = "Zip should be positive")
    private String zip;
    /**
     * address
     */
    @NotBlank(message = "address needed")
    private String address;
    /**
     * city
     */
    @NotBlank(message = "city needed")
    private String city;
    /**
     * email
     */
    @NotBlank(message = "email needed")
    @Email
    private String email;

    /**
     * default constructor
     */
    public Persons(){
        super();
    }

    /**
     * Persons constructor
     * @param firstName first name
     * @param lastName last name
     * @param phone phone number
     * @param zip ZIP code
     * @param address address
     * @param city city
     * @param email email
     */
    @JsonCreator
    public Persons(@JsonProperty("firstName") final String firstName, @JsonProperty("lastName") final String lastName, @JsonProperty("phone") final String phone, @JsonProperty("zip") final String zip, @JsonProperty("address") final String address, @JsonProperty("city") final String city, @JsonProperty("email") final String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.zip = zip;
        this.address = address;
        this.city = city;
        this.email = email;
    }

    /**
     * getter first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * getter last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * getter phone number
     * @return phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * getter ZIP code
     * @return ZIP code
     */
    public String getZip() {
        return zip;
    }

    /**
     * getter address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * getter city
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * getter email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter first name
     * @param firstName
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * setter last name
     * @param lastName
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * setter phone number
     * @param phone
     */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    /**
     * setter ZIP code
     * @param zip
     */
    public void setZip(final String zip) {
        this.zip = zip;
    }

    /**
     * setter address
     * @param address
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    /**
     * setter city
     * @param city
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * setter email
     * @param email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * define toString()
     * @return ""+firstName+" "+lastName+" / "+address+" "+zip+" "+city+" / email: "+email+" / phone: "+phone+"\n"
     */
    @Override
    public String toString() {
        return ""+firstName+" "+lastName+" / "+address+" "+zip+" "+city+" / email: "+email+" / phone: "+phone+"\n";
    }
}
