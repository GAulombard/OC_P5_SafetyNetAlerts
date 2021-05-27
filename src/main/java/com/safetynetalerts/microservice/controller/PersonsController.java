package com.safetynetalerts.microservice.controller;

import com.safetynetalerts.microservice.DAO.PersonsDAO;
import com.safetynetalerts.microservice.model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

@RestController
public class PersonsController {

    @Autowired
    private PersonsDAO personsDAO;

    //persons
    @GetMapping(value = "persons")
    public Set<Persons> showListPersons() throws IOException {
        return personsDAO.findAll();
    }

    //persons/{phone}
    @GetMapping(value = "persons/{phone}")
    public Persons showPersonsByPhone(@PathVariable String phone) throws IOException {
        return personsDAO.findByPhone(phone);
    }

    //person
    @PostMapping(value="person")
    public void createPerson(@RequestBody Persons newPerson) {
        personsDAO.save(newPerson);
    }

    @PutMapping(value="person")
    public void updatePerson(@RequestBody Persons person) {
        personsDAO.update(person);
    }

}
