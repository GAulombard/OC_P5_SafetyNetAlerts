package com.safetynetalerts.microservice.controller;

import com.safetynetalerts.microservice.DAO.FireStationsDAO;
import com.safetynetalerts.microservice.DAO.MedicalRecordsDAO;
import com.safetynetalerts.microservice.DAO.PersonsDAO;
import com.safetynetalerts.microservice.constants.Constants;
import com.safetynetalerts.microservice.model.MedicalRecords;
import com.safetynetalerts.microservice.model.Persons;
import com.safetynetalerts.microservice.util.CalculateAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@RestController
public class URLSController {

    private static final Logger LOGGER = LogManager.getLogger(PersonsController.class);

    @Autowired
    private PersonsDAO personsDAO;
    @Autowired
    private FireStationsDAO fireStationsDAO;
    @Autowired
    private MedicalRecordsDAO medicalRecordsDAO;

    @GetMapping(value = "firestation")
    public Set<String> getListOfPersonsCoveredByStationNumber(@RequestParam(value = "stationNumber") final int stationNumber) {
        Set<String> result = new HashSet<>();
        Set<String> coveredAddressList = fireStationsDAO.getListOfAllAddressByStationNumber(stationNumber);
        Set<MedicalRecords> medicalRecordsCovered = new HashSet<>();
        CalculateAge age = new CalculateAge();
        AtomicInteger nbrAdults = new AtomicInteger();
        AtomicInteger nbrChildren = new AtomicInteger();

        //test commentaire
        if(coveredAddressList != null) {
            Set<Persons> personsCoveredByStation = new HashSet<>();
            coveredAddressList.iterator().forEachRemaining(address -> {
                Set<Persons> listTemp = personsDAO.findPersonsByAddress(address);
                personsCoveredByStation.addAll(listTemp);

            });

            personsCoveredByStation.iterator().forEachRemaining(person -> {
                medicalRecordsCovered.add(medicalRecordsDAO.findByFirstAndLastName(person.getFirstName(),person.getLastName()));
                if(age.calculate(medicalRecordsDAO.findByFirstAndLastName(person.getFirstName(),person.getLastName())) < Constants.ADULT_AGE) {
                    nbrChildren.getAndIncrement();
                } else {
                    nbrAdults.getAndIncrement();
                }
            });


            result.add("adults: "+nbrAdults+" children: "+nbrChildren);

            personsCoveredByStation.iterator().forEachRemaining(person -> {
                result.add(person.getFirstName() + " " + person.getLastName() + " / address: " + person.getAddress() + " / phone: " + person.getPhone());
            });
            LOGGER.info("All persons covered by the station "+stationNumber+" were found : \n"+result);
            return result;
        } else {
            LOGGER.info("No address covered by the station "+stationNumber+" found.");
            return result;
        }


    }

}
