package com.safetynetalerts.microservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@JsonTypeName("medicalrecords")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedicalRecords {

    private String firstName;
    private String lastName;
    private Date birthDate;
    private List<String> medications;
    private List<String> allergies;

    public MedicalRecords(){
        super();
    }

    @JsonCreator
    public MedicalRecords(@JsonProperty("firstname") final String firstName, @JsonProperty("lastname") final String lastName, @JsonDeserialize(using = LocalDateDeserializer.class) @JsonSerialize(using = LocalDateSerializer.class) @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy") @JsonProperty("birthdate") final Date birthDate, @JsonProperty("medications") final List<String> medication, @JsonProperty("allergies") final List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.medications = medication;
        this.allergies = allergies;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(final List<String> medication) {
        this.medications = medication;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(final List<String> allergies) {
        this.allergies = allergies;
    }
}
