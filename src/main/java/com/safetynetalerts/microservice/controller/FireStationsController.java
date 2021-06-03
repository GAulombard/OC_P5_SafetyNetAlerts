package com.safetynetalerts.microservice.controller;

import com.safetynetalerts.microservice.DAO.FireStationsDAO;
import com.safetynetalerts.microservice.model.FireStations;
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
public class FireStationsController {

    private static final Logger LOGGER = LogManager.getLogger(FireStationsController.class);

    @Autowired
    private FireStationsDAO fireStationsDAO;

    @GetMapping(value = "firestations")
    public Set<FireStations> showListFireStations() throws IOException {
        Set<FireStations> result = fireStationsDAO.findAll();
        LOGGER.info("Get the list of all fire stations : \n{}",result.toString());
        return result;
    }

    @PostMapping(value="firestation")
    public void createFireStation(@RequestBody FireStations newFireStation) {
        if(fireStationsDAO.save(newFireStation)) {
            LOGGER.info("new fire station saved :",newFireStation.toString());
        }
        else {
            LOGGER.info("ERROR new fire station cannot be saved");
        }
    }

}
