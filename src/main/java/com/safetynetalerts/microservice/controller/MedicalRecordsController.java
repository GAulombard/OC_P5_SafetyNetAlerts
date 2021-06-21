package com.safetynetalerts.microservice.controller;

import com.safetynetalerts.microservice.DAO.MedicalRecordsDAO;
import com.safetynetalerts.microservice.exceptions.AlreadyExistException;
import com.safetynetalerts.microservice.exceptions.NotFoundException;
import com.safetynetalerts.microservice.model.MedicalRecords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.Set;

/**
 * detail for MedicalRecordsController
 */
@RestController
public class MedicalRecordsController {

    /**
     * LOGGER
     *
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(MedicalRecordsController.class);
    /**
     * medical record DAO
     *
     * @see MedicalRecordsDAO
     */
    @Autowired
    private MedicalRecordsDAO medicalRecordsDAO;

    /**
     * get a list of all medical records
     *
     * @return Http status 200 - OK in all case
     * @throws IOException
     */
    @GetMapping(value = "medicalrecords")
    public Set<MedicalRecords> showListMedicalRecords() throws IOException {
        Set<MedicalRecords> result = medicalRecordsDAO.findAll();
        LOGGER.info("List of all medical records generated");
        return result;
    }

    /**
     * create a new medical records
     * return Http status 400 - bad request when invalid argument
     * return Http status 409 - conflict when medical record already exist then throw AlreadyExistException
     *
     * @param newMedicalRecord medical records
     * @return Http status 201 - created
     * @see MedicalRecords
     */
    @PostMapping(value = "medicalrecord")
    public ResponseEntity<Void> createMedicalRecord(@Valid @RequestBody MedicalRecords newMedicalRecord) {
        if (medicalRecordsDAO.save(newMedicalRecord)) {
            LOGGER.info("New medical record saved");
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/search/{firstName}_{lastName}")
                    .buildAndExpand(newMedicalRecord.getFirstName(), newMedicalRecord.getLastName())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            RuntimeException e = new AlreadyExistException("ERROR : Medical record for: " + newMedicalRecord.getFirstName() + " " + newMedicalRecord.getLastName() + ", already exist");
            LOGGER.error(e);
            throw e;
        }
    }

    /**
     * delete a medical record by first and last name
     * return Http status 404 - Not Found if first or last name not found then throw NotFoundException
     *
     * @param firstName first name
     * @param lastName  last name
     * @return Http status 200 - OK
     */
    @DeleteMapping(value = "medicalrecord/{firstName}_{lastName}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable final String firstName, @PathVariable final String lastName) {
        if (medicalRecordsDAO.deleteByFirstAndLastName(firstName, lastName)) {
            LOGGER.info("Medical record deleted");
            return ResponseEntity.ok().build();
        } else {
            RuntimeException e = new NotFoundException("ERROR : Medical record for" + firstName + " " + lastName + ", doesn't exist");
            LOGGER.error(e);
            throw e;
        }
    }

    /**
     * update a medical record
     * return Http status 404 - Not Found if the medical record not found then throw NotFoundException
     *
     * @param medicalRecord medical record
     * @return Http status 200 - OK
     */
    @PutMapping(value = "medicalrecord")
    public ResponseEntity<Void> updateMedicalRecord(@Valid @RequestBody MedicalRecords medicalRecord) {
        if (medicalRecordsDAO.update(medicalRecord)) {
            LOGGER.info("Medical record updated");
            return ResponseEntity.ok().build();
        } else {
            RuntimeException e = new NotFoundException("ERROR : Medical record for: " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + ", doesn't exist and cannot be updated");
            LOGGER.error(e);
            throw e;
        }
    }

    /**
     * get a medical record by first and last name
     * return Http status 404 - Not found if the first or last name not found then throw NotFoundexception
     *
     * @param firstName first name
     * @param lastName  last name
     * @return Http status 200 - OK
     * @throws IOException
     * @see MedicalRecords
     */
    @GetMapping(value = "medicalrecord/search/{firstName}_{lastName}")
    public MedicalRecords getMedicalRecordsByFirstAndLastName(@PathVariable String firstName, @PathVariable String lastName) throws IOException {
        MedicalRecords result = medicalRecordsDAO.findByFirstAndLastName(firstName, lastName);

        if (result != null) {
            LOGGER.info("Get medical records for : " + firstName + " " + lastName);
            return result;
        } else {
            RuntimeException e = new NotFoundException("ERROR : Medical record for: " + firstName + " " + lastName + ", doesn't exist and cannot be updated");
            LOGGER.error(e);
            throw e;
        }
    }
}
