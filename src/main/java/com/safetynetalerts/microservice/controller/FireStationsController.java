package com.safetynetalerts.microservice.controller;

import com.safetynetalerts.microservice.DAO.FireStationsDAO;
import com.safetynetalerts.microservice.exceptions.AlreadyExistException;
import com.safetynetalerts.microservice.exceptions.NotFoundException;
import com.safetynetalerts.microservice.model.FireStations;
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
public class FireStationsController {

    private static final Logger LOGGER = LogManager.getLogger(FireStationsController.class);

    @Autowired
    private FireStationsDAO fireStationsDAO;

    @GetMapping(value = "firestations")
    public Set<FireStations> showListFireStations() throws IOException {
        Set<FireStations> result = fireStationsDAO.findAll();
        LOGGER.info("List of all fire stations generated");
        return result;
    }

    @PostMapping(value="firestation")
    public ResponseEntity<Void> createFireStation(@Valid @RequestBody FireStations newFireStation) {
        if(fireStationsDAO.save(newFireStation)) {
            LOGGER.info("New fire station saved");
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/search/{stationNumber}")
                    .buildAndExpand(newFireStation.getStation())
                    .toUri();
            return ResponseEntity.created(location).build();
        }
        else {
            RuntimeException e = new AlreadyExistException("ERROR : station: " + newFireStation.getStation() + ", at " + newFireStation.getAddress() + " already exist");
            LOGGER.error(e);
            throw e;
        }
    }

    @DeleteMapping(value="firestation/station/{station}")
    public ResponseEntity<Void> deleteByStation(@PathVariable final int station) {
        if(fireStationsDAO.deleteFireStationsByNumber(station)) {
            LOGGER.info("Fire station deleted");
            return ResponseEntity.ok().build();
        } else {
            RuntimeException e = new NotFoundException("ERROR : firestation number: " + station + ", doesn't exist");
            LOGGER.error(e);
            throw e;
        }
    }

    @DeleteMapping(value="firestation/address/{address}")
    public ResponseEntity<Void> deleteByAddress(@PathVariable final String address) {
        if(fireStationsDAO.deleteFireStationsByAddress(address)) {
            LOGGER.info("Fire stations deleted");
            return ResponseEntity.ok().build();
        } else {
            RuntimeException e = new NotFoundException("ERROR : firestation at: " + address + ", doesn't exist");
            LOGGER.error(e);
            throw e;
        }
    }

    @PutMapping(value="firestation")
    public ResponseEntity<Void> updateStationNumber(@Valid @RequestBody final FireStations fireStation) {
        if(fireStationsDAO.update(fireStation)) {
            LOGGER.info("Fire station updated");
            return ResponseEntity.ok().build();
        } else {
            RuntimeException e = new NotFoundException("ERROR : firestation: " + fireStation.getStation()+" at,"+fireStation.getAddress() + " doesn't exist");
            LOGGER.error(e);
            throw e;
        }
    }

    @GetMapping(value = "firestation/search/{stationNumber}")
    public Set<FireStations> getFireStationByStationNumber(@PathVariable int stationNumber) throws IOException {
        Set<FireStations> result = fireStationsDAO.findFireStationsByStationNumber(stationNumber);
        if (result != null) {
            LOGGER.info("List of all fire stations at number : "+stationNumber+" generated");
            return result;
        }else {
            RuntimeException e = new NotFoundException("ERROR : Station number: " + stationNumber + ", doesn't exist");
            LOGGER.error(e);
            throw e;
        }
    }

}
