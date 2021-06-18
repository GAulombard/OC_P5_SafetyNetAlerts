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

@RestController
public class MedicalRecordsController {


    private static final Logger LOGGER = LogManager.getLogger(MedicalRecordsController.class);

    @Autowired
    private MedicalRecordsDAO medicalRecordsDAO;

    @GetMapping(value = "medicalrecords")
    public Set<MedicalRecords> showListMedicalRecords() throws IOException {
        Set<MedicalRecords> result = medicalRecordsDAO.findAll();
        LOGGER.info("List of all medical records generated");
        return result;
    }

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

    @PutMapping(value="medicalrecord")
    public ResponseEntity<Void> updateMedicalRecord(@Valid @RequestBody MedicalRecords medicalRecord) {
        if(medicalRecordsDAO.update(medicalRecord)) {
            LOGGER.info("Medical record updated");
            return ResponseEntity.ok().build();
        } else {
            RuntimeException e = new NotFoundException("ERROR : Medical record for: "+medicalRecord.getFirstName()+" "+medicalRecord.getLastName()+", doesn't exist and cannot be updated");
            LOGGER.error(e);
            throw e;
        }
    }

    @GetMapping(value = "medicalrecord/search/{firstName}_{lastName}")
    public MedicalRecords getMedicalRecordsByFirstAndLastName(@PathVariable String firstName,@PathVariable String lastName) throws IOException {
            MedicalRecords result = medicalRecordsDAO.findByFirstAndLastName(firstName,lastName);

            if(result !=null) {
                LOGGER.info("Get medical records for : "+firstName+" "+lastName);
                return result;
            }else {
                RuntimeException e = new NotFoundException("ERROR : Medical record for: "+firstName+" "+lastName+", doesn't exist and cannot be updated");
                LOGGER.error(e);
                throw e;
            }
    }
}
