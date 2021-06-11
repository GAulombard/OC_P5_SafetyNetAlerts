package com.safetynetalerts.microservice.datasource;

import com.safetynetalerts.microservice.model.FireStations;
import com.safetynetalerts.microservice.model.MedicalRecords;
import com.safetynetalerts.microservice.model.Persons;

import java.util.*;

public class DataBaseTestService {

    private DataBase dataBase;

    public void clearDBTest() {
        dataBase = DataBaseManager.INSTANCE.getDataBase();
        dataBase.getPersons().clear();
        dataBase.getMedicalRecords().clear();
        dataBase.getFireStations().clear();
    }

    public void restoreDBTest() {
        dataBase = DataBaseManager.INSTANCE.getDataBase();

        Set<Persons> persons = dataBase.getPersons();
        Set<FireStations> fireStations = dataBase.getFireStations();
        Set<MedicalRecords> medicalRecords = dataBase.getMedicalRecords();

        Persons person1 = new Persons("firstName", "lastName", "123-456-7890", "123", "address", "city", "mail@email.com");
        Persons person2 = new Persons("firstName2", "lastName2", "123-456-78903", "123", "address2", "city", "mail2@email.com");

        FireStations fireStation1 = new FireStations("address", 1);
        FireStations fireStation2 = new FireStations("address2", 2);

        Date birthdate1 = new Date(1994, 6, 15);
        Date birthdate2 = new Date(2012, 6, 15);
        List<String> medicationList = new ArrayList<>();
        medicationList.add("medication1:300mg");
        medicationList.add("medication2:100mg");
        List<String> allergieList = new ArrayList<>();
        allergieList.add("allergie1");
        allergieList.add("allergie2");
        allergieList.add("allergie3");
        MedicalRecords medicalRecord1 = new MedicalRecords("firstName", "lastName", birthdate1, medicationList, allergieList);
        MedicalRecords medicalRecord2 = new MedicalRecords("firstName2", "lastName2", birthdate2, null, null);

        persons.add(person1);
        persons.add(person2);
        fireStations.add(fireStation1);
        fireStations.add(fireStation2);
        medicalRecords.add(medicalRecord1);
        medicalRecords.add(medicalRecord2);
    }
}