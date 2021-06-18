package com.safetynetalerts.microservice.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

/**
 * Medical Records details
 */
@JsonTypeName("medicalrecords")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedicalRecords {
    /**
     * first name
     */
    //@NotBlank(message = "First name needed")
    private String firstName;
    /**
     * last name
     */
    //@NotBlank(message = "Last name needed")
    private String lastName;
    /**
     * birth date
     *
     * @see Date
     */
    private Date birthDate;
    /**
     * list of medications
     */
    private List<String> medications;
    /**
     * list of allergies
     */
    private List<String> allergies;

    /**
     * default constructor
     */
    public MedicalRecords() {
        super();
    }

    /**
     * Medical Records constructor
     *
     * @param firstName  first name
     * @param lastName   last name
     * @param birthDate  birth date
     * @param medication list of medications
     * @param allergies  list of allergies
     */
    @JsonCreator
    public MedicalRecords(@JsonProperty("firstname") final String firstName, @JsonProperty("lastname") final String lastName, @JsonDeserialize(using = DateDeserializers.DateDeserializer.class) @JsonSerialize(using = DateSerializer.class) @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy") @JsonProperty("birthdate") final Date birthDate, @JsonProperty("medications") final List<String> medication, @JsonProperty("allergies") final List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.medications = medication;
        this.allergies = allergies;
    }

    /**
     * getter first name
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * setter first name
     *
     * @param firstName
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * getter last name
     *
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * setter last name
     *
     * @param lastName
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * getter birth date
     *
     * @return birth date
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * setter birth date
     *
     * @param birthDate
     */
    public void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * getter list of medications
     *
     * @return list of medications
     */
    public List<String> getMedications() {
        return medications;
    }

    /**
     * setter list of medications
     *
     * @param medication
     */
    public void setMedications(final List<String> medication) {
        this.medications = medication;
    }

    /**
     * getter list of allergies
     *
     * @return list of allergies
     */
    public List<String> getAllergies() {
        return allergies;
    }

    /**
     * setter list of allergies
     *
     * @param allergies
     */
    public void setAllergies(final List<String> allergies) {
        this.allergies = allergies;
    }

    /**
     * define toString()
     *
     * @return ""+firstName+" "+lastName+" / birth date: "+birthDate+" / medications: "+medications+" / allergies: "+allergies+"\n"
     */
    @Override
    public String toString() {
        return "" + firstName + " " + lastName + " / birth date: " + birthDate + " / medications: " + medications + " / allergies: " + allergies + "\n";
    }
}
