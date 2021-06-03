package com.safetynetalerts.microservice.controller;

import com.safetynetalerts.microservice.DAO.MedicalRecordsDAO;
import com.safetynetalerts.microservice.model.MedicalRecords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Set;

@RestController
public class MedicalRecordsController {


    private static final Logger LOGGER = LogManager.getLogger(MedicalRecordsController.class);

    @Autowired
    private MedicalRecordsDAO medicalRecordsDAO;

    @GetMapping(value = "medicalrecords")
    public Set<MedicalRecords> showListMedicalRecords() throws IOException {
        Set<MedicalRecords> result = medicalRecordsDAO.findAll();
        LOGGER.info("Get the list of all medical records : \n{}",result.toString());
        return result;
    }

    @PostMapping(value="medicalrecord")
    public void createMedicalRecord(@RequestBody MedicalRecords newMedicalRecord) {
        if(medicalRecordsDAO.save(newMedicalRecord)) {
            LOGGER.info("new medical record saved : {}",newMedicalRecord.toString());
        }
        else {
            LOGGER.info("ERROR new medical record cannot be saved");
        }
    }

}
