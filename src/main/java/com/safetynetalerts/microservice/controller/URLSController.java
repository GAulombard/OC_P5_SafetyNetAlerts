package com.safetynetalerts.microservice.controller;

import com.safetynetalerts.microservice.DAO.FireStationsDAO;
import com.safetynetalerts.microservice.DAO.MedicalRecordsDAO;
import com.safetynetalerts.microservice.DAO.PersonsDAO;
import com.safetynetalerts.microservice.constants.Constants;
import com.safetynetalerts.microservice.exceptions.NotFoundException;
import com.safetynetalerts.microservice.model.DTO.*;
import com.safetynetalerts.microservice.model.MedicalRecords;
import com.safetynetalerts.microservice.model.Persons;
import com.safetynetalerts.microservice.util.CalculateAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

            LOGGER.info("List of all persons covered by the station " + stationNumber + " generated");
            return result;
        } else {
            RuntimeException e = new NotFoundException("No address covered by the station " + stationNumber + " found, or station number doesn't exist");
            LOGGER.error(e);
            throw e;
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
            LOGGER.info("List of children by address generated");
            return result;
        } else {
            RuntimeException e = new NotFoundException("address: " + address + " doesn't exist.");
            LOGGER.error(e);
            throw e;
        }
    }

    @GetMapping(value = "phoneAlert")
    public Set<String> getAllPhoneByStationNumber(@RequestParam(value = "firestation") final int stationNumber) {
        Set<String> result = new HashSet<>();
        Set<String> allAddress = fireStationsDAO.getListOfAllAddressByStationNumber(stationNumber);
        Set<Persons> allPersons = new HashSet<>();

        if (allAddress != null) {
            allAddress.iterator().forEachRemaining(address -> {
                allPersons.addAll(personsDAO.getListOfAllPersonsByAddress(address));
            });

            allPersons.iterator().forEachRemaining(person -> {
                result.add(person.getPhone());
            });
            LOGGER.info("List of phones generated");
            return result;
        } else {
            RuntimeException e = new NotFoundException("ERROR: Station number: "+stationNumber+" doesn't exist");
            LOGGER.error(e);
            throw e;
        }

    }

    @GetMapping(value = "fire")
    public Set<FireDTO> getPersonsByAddressAndStation(@RequestParam(value = "address") final String address) {
        Set<FireDTO> result = new HashSet<>();
        Set<Persons> personsAtAddress = personsDAO.getListOfAllPersonsByAddress(address);

        if (personsAtAddress != null) {
            personsAtAddress.iterator().forEachRemaining(person -> {
                int age = new CalculateAge().calculate(medicalRecordsDAO.findByFirstAndLastName(person.getFirstName(), person.getLastName()));

                FireDTO fireDTO = new FireDTO();
                fireDTO.setFirstName(person.getFirstName());
                fireDTO.setLastName(person.getLastName());
                fireDTO.setPhone(person.getPhone());
                fireDTO.setStationNumber(fireStationsDAO.findFireStationsNumberByAddress(address));
                fireDTO.setAge(age);
                fireDTO.setMedicalBackgroundDTO(medicalRecordsDAO.getMedicalBackgroundByFirstAndLastName(person.getFirstName(), person.getLastName()));

                result.add(fireDTO);
            });
            LOGGER.info("List of persons by address and station number generated");
            return result;
        } else {
            RuntimeException e = new NotFoundException("ERROR: address: "+address+" doesn't exist");
            LOGGER.error(e);
            throw e;
        }


    }


    @GetMapping(value = "flood/stations")
    public Set<FloodDTO> getHomesByStationsNumber(@RequestParam(value = "stations") final int stationNumber) {
        Set<FloodDTO> result = new HashSet<>();
        Set<String> allAddressList = fireStationsDAO.findAddressByStationNumber(stationNumber);

        if (allAddressList != null) {
            allAddressList.iterator().forEachRemaining(address -> {
                FloodDTO floodDTO = new FloodDTO();
                Set<PersonInfoMedicalDTO> personInfoMedicalDTOList = new HashSet<>();
                floodDTO.setAddress(address);
                Set<Persons> personsAtAddress = personsDAO.getListOfAllPersonsByAddress(address);
                personsAtAddress.iterator().forEachRemaining(person -> {
                    int age = new CalculateAge().calculate(medicalRecordsDAO.findByFirstAndLastName(person.getFirstName(), person.getLastName()));

                    PersonInfoMedicalDTO personInfoMedical = new PersonInfoMedicalDTO();
                    personInfoMedical.setFirstName(person.getFirstName());
                    personInfoMedical.setLastName(person.getLastName());
                    personInfoMedical.setPhone(person.getPhone());
                    personInfoMedical.setAge(age);
                    personInfoMedical.setMedicalBackgroundDTO(medicalRecordsDAO.getMedicalBackgroundByFirstAndLastName(person.getFirstName(), person.getLastName()));
                    personInfoMedicalDTOList.add(personInfoMedical);
                    floodDTO.setPersonInfoMedical(personInfoMedicalDTOList);
                });

                result.add(floodDTO);
            });
            LOGGER.info("List of homes by station number generated");
            return result;
        } else {
            RuntimeException e = new NotFoundException("ERROR: station number: "+stationNumber+" doesn't exist");
            LOGGER.error(e);
            throw e;
        }


    }

    @GetMapping(value = "personInfo")
    public Set<PersonFullInfoDTO> getAllInformationByFirstAndLastName(@RequestParam(value = "firstName") final String firstName, @RequestParam(value = "lastName") final String lastName) {
        Set<PersonFullInfoDTO> result = new HashSet<>();
        Set<Persons> allPersons = personsDAO.findAllByFirstAndLastName(firstName, lastName);

        if (allPersons != null) {

            allPersons.iterator().forEachRemaining(person -> {
                int age = new CalculateAge().calculate(medicalRecordsDAO.findByFirstAndLastName(person.getFirstName(), person.getLastName()));

                PersonFullInfoDTO personFullInfoDTO = new PersonFullInfoDTO();
                personFullInfoDTO.setFirstName(person.getFirstName());
                personFullInfoDTO.setLastName(person.getLastName());
                personFullInfoDTO.setAge(age);
                personFullInfoDTO.setAddress(person.getAddress());
                personFullInfoDTO.setPhone(person.getPhone());
                personFullInfoDTO.setEmail(person.getEmail());
                personFullInfoDTO.setMedicalBackgroundDTO(medicalRecordsDAO.getMedicalBackgroundByFirstAndLastName(person.getFirstName(), person.getLastName()));

                result.add(personFullInfoDTO);
            });
            LOGGER.info("Information by first and last name generated");
            return result;
        } else {
            RuntimeException e = new NotFoundException("ERROR: person: "+firstName+" "+lastName+", doesn't exist and cannot generated information");
            LOGGER.error(e);
            throw e;
        }

    }

    @GetMapping(value = "communityEmail")
    public List<String> getAllEmailByCity(@RequestParam(value = "city") final String city) {
        List<String> result = new ArrayList<>();
        Set<Persons> allPersons = personsDAO.getListOfAllPersonsByCity(city);

        if (allPersons != null) {
            allPersons.iterator().forEachRemaining(person -> {
                result.add(person.getEmail());
            });
            LOGGER.info("List of eMail generated");
            return result;
        } else {
            RuntimeException e = new NotFoundException("ERROR: city: "+city+", doesn't exist");
            LOGGER.error(e);
            throw e;
        }
    }
}
