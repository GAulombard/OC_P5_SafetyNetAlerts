package com.safetynetalerts.microservice.controller;

import com.safetynetalerts.microservice.DAO.PersonsDAO;
import com.safetynetalerts.microservice.model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class PersonsController {

    private static final Logger LOGGER = LogManager.getLogger(PersonsController.class);

    @Autowired
    private PersonsDAO personsDAO;

    //persons
    @GetMapping(value = "persons")
    public Set<Persons> showListPersons() throws IOException {
        Set<Persons> result = personsDAO.findAll();
        LOGGER.info("Get the list of all persons : {}",result);
        return result;
    }

    //persons/{phone}
    @GetMapping(value = "person/{phone}")
    public Persons showPersonsByPhone(@PathVariable String phone) throws IOException {
        return personsDAO.findByPhone(phone);
    }

    //person
    @PostMapping(value="person")
    public void createPerson(@RequestBody Persons newPerson) {
        if(personsDAO.save(newPerson)) {
            LOGGER.info("new person saved :",newPerson);
        }
        else {
            LOGGER.info("ERROR new person cannot be saved");
        }
    }

    @PutMapping(value="person")
    public void updatePerson(@RequestBody Persons person) {
        if(personsDAO.update(person)) {
            LOGGER.info("person updated");
        } else {
            LOGGER.info("ERROR person cannot be updated");
        }
    }

    @DeleteMapping(value="person/{firstName}_{lastName}")
    public void deletePerson(@PathVariable final String firstName, @PathVariable final String lastName){
        if (personsDAO.delete(firstName,lastName)) {
            LOGGER.info("person deleted");
        }
    }

}
