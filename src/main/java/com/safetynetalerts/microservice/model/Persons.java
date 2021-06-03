package com.safetynetalerts.microservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@JsonTypeName("persons")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Persons {

    @NotBlank(message = "First name needed")
    private String firstName;
    @NotBlank(message = "Last name needed")
    private String lastName;
    private String phone;
    @Positive(message = "Zip should be positive")
    private String zip;
    private String address;
    private String city;
    private String email;

    public Persons(){
        super();
    }

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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getZip() {
        return zip;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public void setZip(final String zip) {
        this.zip = zip;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return ""+firstName+" "+lastName+" / "+address+" "+zip+" "+city+" / email: "+email+" / phone: "+phone+"\n";
    }
}
