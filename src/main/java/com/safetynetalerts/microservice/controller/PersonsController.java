package com.safetynetalerts.microservice.controller;

import com.safetynetalerts.microservice.DAO.PersonsDAO;
import com.safetynetalerts.microservice.exceptions.AlreadyExistException;
import com.safetynetalerts.microservice.exceptions.NotFoundException;
import com.safetynetalerts.microservice.model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
public class PersonsController {

    private static final Logger LOGGER = LogManager.getLogger(PersonsController.class);

    @Autowired
    private PersonsDAO personsDAO;


    @GetMapping(value = "persons")
    public Set<Persons> showListPersons() throws IOException {
        Set<Persons> result = personsDAO.findAll();
        LOGGER.info("List of all persons generated");
        return result;
    }


    @GetMapping(value = "person/{phone}")
    public Persons showPersonsByPhone(@PathVariable String phone) throws IOException {

        Persons result = personsDAO.findByPhone(phone);
        if(result != null) {
            LOGGER.info("Person by phone :"+phone+" generated");
            return result;
        }else {
            RuntimeException e = new NotFoundException("ERROR : phone: "+phone+", doesn't exist");
            LOGGER.error(e);
            throw e;
        }





    }


    @PostMapping(value="person")
    public ResponseEntity<Void> createPerson(@Valid @RequestBody Persons newPerson) throws AlreadyExistException {
        if(personsDAO.save(newPerson)) {
            LOGGER.info("New person saved");
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/search/{firstName}_{lastName}")
                    .buildAndExpand(newPerson.getFirstName(),newPerson.getLastName())
                    .toUri();
            return ResponseEntity.created(location).build();
        }
        else {
            RuntimeException e = new AlreadyExistException("ERROR : Person: "+newPerson.getFirstName()+" "+newPerson.getLastName()+", already exist");
            LOGGER.error(e);
            throw e;
        }
    }

    @PutMapping(value="person")
    public ResponseEntity<Void> updatePerson(@Valid @RequestBody Persons person) {
        if(personsDAO.update(person)) {
            LOGGER.info("Person updated");
            return ResponseEntity.ok().build();
        } else {
            RuntimeException e = new NotFoundException("ERROR : Person: "+person.getFirstName()+" "+person.getLastName()+", doesn't exist and cannot be updated");
            LOGGER.error(e);
            throw e;
        }
    }

    @DeleteMapping(value="person/{firstName}_{lastName}")
    public ResponseEntity<Void> deletePerson(@PathVariable final String firstName, @PathVariable final String lastName){
        if (personsDAO.deleteByFirstAndLastName(firstName,lastName)) {
            LOGGER.info("Person deleted");
            return ResponseEntity.ok().build();
        } else {
            RuntimeException e = new NotFoundException("ERROR : Person: "+firstName+" "+lastName+", doesn't exist and cannot be deleted");
            LOGGER.error(e);
            throw e;
        }
    }

    @GetMapping(value = "person/search/{firstName}_{lastName}")
    public Persons getPersonsByFirstAndLastName(@PathVariable String firstName,@PathVariable String lastName){

        Persons result = personsDAO.findByFirstAndLastName(firstName,lastName);
        if(result != null) {
            LOGGER.info("Person: "+firstName+" "+lastName+" found");
            return result;
        } else {
            RuntimeException e = new NotFoundException("ERROR : Person: "+firstName+" "+lastName+", doesn't exist");
            LOGGER.error(e);
            throw e;
        }

    }

}
