package com.safetynetalerts.microservice.DAO.Implements;

import com.safetynetalerts.microservice.DAO.PersonsDAO;
import com.safetynetalerts.microservice.datasource.DataBase;
import com.safetynetalerts.microservice.datasource.DataBaseManager;
import com.safetynetalerts.microservice.model.Persons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@Repository
public class PersonsDAOImp implements PersonsDAO {

    private static final Logger LOGGER = LogManager.getLogger(PersonsDAOImp.class);
    private DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();
    private Set<Persons> persons = dataBase.getPersons();

    @Override
    public Set<Persons> findAll() throws IOException {
        LOGGER.info("Processing to find all persons");
        return persons;
    }

    @Override
    public Persons findByPhone(String phone) throws IOException {
        LOGGER.info("Processing to get a person by phone");

        for (Persons person : persons) {
            if (person.getPhone().equals(phone)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public boolean save(final Persons person) {
        LOGGER.info("Processing to save new person");
        AtomicBoolean alreadyExist = new AtomicBoolean(false);

        persons.iterator().forEachRemaining(temp -> {
           if(temp.getLastName().equals(person.getLastName()) && temp.getFirstName().equals(person.getFirstName())) {
               alreadyExist.set(true);
           }
       });

        if(alreadyExist.getAcquire() == true) return false;
        else return persons.add(person);
    }

    @Override
    public boolean update(Persons person) {
        LOGGER.info("Processing to update a person");
        if (deleteByFirstAndLastName(person.getFirstName(), person.getLastName())) {
            return persons.add(person);
        } else return false;

    }

    @Override
    public Persons findByFirstAndLastName(final String firstName, final String lastName) {
        LOGGER.info("Processing to find a person by first and last name");
        Set<Persons> result = new HashSet<>();
        persons.iterator().forEachRemaining((person) -> {
            if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
                result.add(person);
            }
        });
        if (result.isEmpty()) return null;
        return result.iterator().next();
    }

    @Override
    public boolean deleteByFirstAndLastName(final String firstName, final String lastName) {
        LOGGER.info("Processing to delete a person by first and last name");

        Persons result = findByFirstAndLastName(firstName, lastName);
        if (result == null) return false;
        return persons.remove(result);


    }

    @Override
    public Set<Persons> getListOfAllPersonsByAddress(final String address) {
        LOGGER.info("Processing to find all persons by address");
        Set<Persons> result = new HashSet<>();
        persons.iterator().forEachRemaining(person -> {
            if (person.getAddress().equals(address)) {
                result.add(person);
            }
        });

        return result;
    }

    @Override
    public Set<Persons> findPersonsByAddress(String address) {
        LOGGER.info("Processing to find persons at address");

        Set<Persons> result = new HashSet<>();
        persons.iterator().forEachRemaining(person -> {
            if (person.getAddress().equals(address)) {
                result.add(person);
            }
        });
        return result;
    }

    @Override
    public Set<Persons> findAllByFirstAndLastName(final String firstName, final String lastName) {
        LOGGER.info("Processing to find all persons by first and last name");
        Set<Persons> result = new HashSet<>();
        persons.iterator().forEachRemaining((person) -> {
            if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
                result.add(person);
            }
        });
        if (result.isEmpty()) return null;
        return result;
    }

    @Override
    public Set<Persons> getListOfAllPersonsByCity(final String city) {
        LOGGER.info("Processing to find all persons by city");
        Set<Persons> result = new HashSet<>();
        persons.iterator().forEachRemaining(person -> {
            if (person.getCity().equals(city)) {
                result.add(person);
            }
        });

        return result;
    }
}
