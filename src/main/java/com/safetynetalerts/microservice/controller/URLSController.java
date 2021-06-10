package com.safetynetalerts.microservice.controller;

import com.safetynetalerts.microservice.DAO.FireStationsDAO;
import com.safetynetalerts.microservice.DAO.MedicalRecordsDAO;
import com.safetynetalerts.microservice.DAO.PersonsDAO;
import com.safetynetalerts.microservice.constants.Constants;
import com.safetynetalerts.microservice.model.DTO.ChildAlertDTO;
import com.safetynetalerts.microservice.model.DTO.PersonInfoDTO;
import com.safetynetalerts.microservice.model.DTO.PersonNameDTO;
import com.safetynetalerts.microservice.model.DTO.PersonsCoveredByStationListDTO;
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
    public PersonsCoveredByStationListDTO getListOfPersonsCoveredByStationNumber(@RequestParam(value = "stationNumber") final int stationNumber) {
        PersonsCoveredByStationListDTO result = new PersonsCoveredByStationListDTO();
        Set<String> coveredAddressList = fireStationsDAO.getListOfAllAddressByStationNumber(stationNumber);
        Set<MedicalRecords> medicalRecordsCovered = new HashSet<>();
        CalculateAge age = new CalculateAge();
        Set<PersonInfoDTO> personInfoDTOList = new HashSet<>();
        AtomicInteger nbrAdults = new AtomicInteger();
        AtomicInteger nbrChildren = new AtomicInteger();

        if (coveredAddressList != null) {
            Set<Persons> personsCoveredByStation = new HashSet<>();
            coveredAddressList.iterator().forEachRemaining(address -> {
                Set<Persons> listTemp = personsDAO.findPersonsByAddress(address);
                personsCoveredByStation.addAll(listTemp);

            });

            personsCoveredByStation.iterator().forEachRemaining(personInfo -> {
                PersonInfoDTO newPersonInfo = new PersonInfoDTO();
                newPersonInfo.setFirstName(personInfo.getFirstName());
                newPersonInfo.setLastName(personInfo.getLastName());
                newPersonInfo.setAddress(personInfo.getAddress());
                newPersonInfo.setPhone(personInfo.getPhone());
                newPersonInfo.setEmail(personInfo.getEmail());
                personInfoDTOList.add(newPersonInfo);
            });

            personsCoveredByStation.iterator().forEachRemaining(person -> {
                medicalRecordsCovered.add(medicalRecordsDAO.findByFirstAndLastName(person.getFirstName(), person.getLastName()));
                if (age.calculate(medicalRecordsDAO.findByFirstAndLastName(person.getFirstName(), person.getLastName())) <= Constants.ADULT_AGE) {
                    nbrChildren.getAndIncrement();
                } else {
                    nbrAdults.getAndIncrement();
                }
            });

            result.setNbrAdults(nbrAdults);
            result.setNbrChildren(nbrChildren);
            result.setPersons(personInfoDTOList);

            LOGGER.info("All persons covered by the station " + stationNumber + " were found : {}\n", result.toString());
            return result;
        } else {
            LOGGER.info("No address covered by the station " + stationNumber + " found.");
            return result;
        }


    }


    @GetMapping(value = "childAlert")
    public Set<ChildAlertDTO> getListOfChildrenByAddress(@RequestParam(value = "address") final String address) {
        Set<ChildAlertDTO> result = new HashSet<>();
        Set<Persons> personsByAddress = personsDAO.findPersonsByAddress(address);

        if (personsByAddress != null) {

            Set<PersonNameDTO> otherMemberDTO = new HashSet<>();
            Set<Persons> childrenList = new HashSet<>();

            personsByAddress.iterator().forEachRemaining(person -> {
                int age = new CalculateAge().calculate(medicalRecordsDAO.findByFirstAndLastName(person.getFirstName(), person.getLastName()));
                if (age <= Constants.ADULT_AGE) { //if children
                    childrenList.add(person);

                } else {

                    PersonNameDTO personNameDTO = new PersonNameDTO();
                    personNameDTO.setFirstName(person.getFirstName());
                    personNameDTO.setLastName(person.getLastName());
                    otherMemberDTO.add(personNameDTO);
                }

            });

            childrenList.iterator().forEachRemaining(person -> {
                ChildAlertDTO childAlertDTO = new ChildAlertDTO();
                int age = new CalculateAge().calculate(medicalRecordsDAO.findByFirstAndLastName(person.getFirstName(), person.getLastName()));
                childAlertDTO.setFirstName(person.getFirstName());
                childAlertDTO.setLastName(person.getLastName());
                childAlertDTO.setAge(age);
                childAlertDTO.setOtherMembers(otherMemberDTO);
                result.add(childAlertDTO);
            });

            return result;
        } else {
            return result;
        }


    }

}
