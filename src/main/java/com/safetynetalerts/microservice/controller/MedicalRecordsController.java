package com.safetynetalerts.microservice.controller;

import com.safetynetalerts.microservice.DAO.MedicalRecordsDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalRecordsController {


    private static final Logger LOGGER = LogManager.getLogger(PersonsController.class);

    @Autowired
    private MedicalRecordsDAO medicalRecordsDAO;

}
