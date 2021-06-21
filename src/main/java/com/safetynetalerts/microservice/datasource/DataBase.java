package com.safetynetalerts.microservice.datasource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.safetynetalerts.microservice.model.FireStations;
import com.safetynetalerts.microservice.model.MedicalRecords;
import com.safetynetalerts.microservice.model.Persons;


import java.util.Set;

/**
 * details for DataBase
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataBase {
    /**
     * persons
     *
     * @see Persons
     */
    private Set<Persons> persons;
    /**
     * fire stations
     *
     * @see FireStations
     */
    private Set<FireStations> fireStations;
    /**
     * medical records
     *
     * @see MedicalRecords
     */
    private Set<MedicalRecords> medicalRecords;

    /**
     * default constructor
     */
    public DataBase() {
        super();
    }

    /**
     * constructor
     *
     * @param persons        persons
     * @param fireStations   fire stations
     * @param medicalRecords medical records
     */
    @JsonCreator
    DataBase(@JsonProperty("persons") final Set<Persons> persons, @JsonProperty("firestations") final Set<FireStations> fireStations, @JsonProperty("medicalrecords") final Set<MedicalRecords> medicalRecords) {
        this.persons = persons;
        this.fireStations = fireStations;
        this.medicalRecords = medicalRecords;
    }

    /**
     * getter
     *
     * @return persons
     */
    public Set<Persons> getPersons() {
        return persons;
    }

    /**
     * setter
     *
     * @param persons persons
     */
    public void setPersons(final Set<Persons> persons) {
        this.persons = persons;
    }

    /**
     * getter
     *
     * @return fire stations
     */
    public Set<FireStations> getFireStations() {
        return fireStations;
    }

    /**
     * setter
     *
     * @param fireStations fire stations
     */
    public void setFireStations(final Set<FireStations> fireStations) {
        this.fireStations = fireStations;
    }

    /**
     * getter
     *
     * @return medical records
     */
    public Set<MedicalRecords> getMedicalRecords() {
        return medicalRecords;
    }

    /**
     * setter
     *
     * @param medicalRecords medical records
     */
    public void setMedicalRecords(final Set<MedicalRecords> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    /**
     * define the toString() method
     *
     * @return
     */
    @Override
    public String toString() {
        return "" + persons + "\n" + fireStations + "\n" + medicalRecords + "";
    }
}
