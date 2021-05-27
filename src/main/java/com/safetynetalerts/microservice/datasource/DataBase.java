package com.safetynetalerts.microservice.datasource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;
import com.safetynetalerts.microservice.model.FireStations;
import com.safetynetalerts.microservice.model.MedicalRecords;
import com.safetynetalerts.microservice.model.Persons;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataBase {
    private Set<Persons> persons;
    private Set<FireStations> fireStations;
    private Set<MedicalRecords> medicalRecords;

    public DataBase() {
        super();
    }

    @JsonCreator
    DataBase(@JsonProperty("persons") final Set<Persons> persons,@JsonProperty("firestations") final Set<FireStations> fireStations,@JsonProperty("medicalrecords") final Set<MedicalRecords> medicalRecords) {
        this.persons = persons;
        this.fireStations = fireStations;
        this.medicalRecords = medicalRecords;
    }

    public Set<Persons> getPersons() {
        return persons;
    }

    public void setPersons(final Set<Persons> persons) {
        this.persons = persons;
    }

    public Set<FireStations> getFireStations() {
        return fireStations;
    }

    public void setFireStations(final Set<FireStations> fireStations) {
        this.fireStations = fireStations;
    }

    public Set<MedicalRecords> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(final Set<MedicalRecords> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }
}
