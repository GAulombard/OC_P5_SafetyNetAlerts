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
        LOGGER.info("Get the list of all persons :\n {}",result.toString());
        return result;
    }


    @GetMapping(value = "person/{phone}")
    public Persons showPersonsByPhone(@PathVariable String phone) throws IOException {
        return personsDAO.findByPhone(phone);
    }


    @PostMapping(value="person")
    public ResponseEntity<Void> createPerson(@Valid @RequestBody Persons newPerson) {
        if(personsDAO.save(newPerson)) {
            LOGGER.info("new person saved : {}",newPerson.toString());
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{phone}")
                    .buildAndExpand(newPerson.getPhone())
                    .toUri();
            return ResponseEntity.created(location).build();
        }
        else {
            RuntimeException e = new AlreadyExistException("ERROR :"+newPerson.getFirstName()+" "+newPerson.getLastName()+" already exist");
            LOGGER.error(e);
            throw e;
            //return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(value="person")
    public ResponseEntity<Void> updatePerson(@Valid @RequestBody Persons person) {
        if(personsDAO.update(person)) {
            LOGGER.info("person updated : {}",person.toString());
            return ResponseEntity.ok().build();
        } else {
            RuntimeException e = new NotFoundException("ERROR :"+person.getFirstName()+" "+person.getLastName()+" doesn't exist and cannot be updated");
            LOGGER.error(e);
            throw e;
            //return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value="person/{firstName}_{lastName}")
    public ResponseEntity<Void> deletePerson(@PathVariable final String firstName, @PathVariable final String lastName){
        if (personsDAO.deleteByFirstAndLastName(firstName,lastName)) {
            LOGGER.info("person deleted :"+firstName+" "+lastName);
            return ResponseEntity.ok().build();
        } else {
            throw new NotFoundException("ERROR :"+firstName+" "+lastName+" doesn't exist and cannot be deleted");
            //RuntimeException e = new NotFoundException("ERROR :"+firstName+" "+lastName+" doesn't exist and cannot be deleted");
            //LOGGER.error(e);
            //throw e;
        }
    }

}
