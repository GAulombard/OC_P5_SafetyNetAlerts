package com.safetynetalerts.microservice.controller;

import com.safetynetalerts.microservice.DAO.PersonsDAO;
import com.safetynetalerts.microservice.model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
public class PersonsController {

    @Autowired
    private PersonsDAO personsDAO;

    //persons/{firstName,LastName,phone,zip,address,city,email}
    @PutMapping(value = "persons/{firstName,lastName,phone,zip,address,city,email}")
    public void createPersons(@PathVariable String firstName, @PathVariable String lastName, @PathVariable String phone, @PathVariable String zip, @PathVariable String address, @PathVariable String city, @PathVariable String email) {
        Persons newPersons = new Persons(firstName, lastName, phone, zip, address, city, email);
    }

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

}
