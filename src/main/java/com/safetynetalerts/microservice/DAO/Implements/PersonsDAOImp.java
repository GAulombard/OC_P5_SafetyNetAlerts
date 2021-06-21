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

/**
 * details for PersonsDAOImp
 */
@Repository
public class PersonsDAOImp implements PersonsDAO {
    /**
     * LOGGER
     *
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(PersonsDAOImp.class);
    /**
     * data base
     *
     * @see DataBase
     */
    private DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();
    /**
     * persons
     *
     * @see Persons
     */
    private Set<Persons> persons = dataBase.getPersons();

    /**
     * get a list of all persons
     *
     * @return list of all persons
     * @throws IOException
     * @see Persons
     */
    @Override
    public Set<Persons> findAll() throws IOException {
        LOGGER.info("Processing to find all persons");
        return persons;
    }

    /**
     * find a person by phone
     *
     * @param phone phone
     * @return person
     * @throws IOException
     * @see Persons
     */
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

    /**
     * save a new person
     * return false if the new person not saved
     *
     * @param person person
     * @return boolean
     * @see Persons
     */
    @Override
    public boolean save(final Persons person) {
        LOGGER.info("Processing to save new person");
        AtomicBoolean alreadyExist = new AtomicBoolean(false);

        persons.iterator().forEachRemaining(temp -> {
            if (temp.getLastName().equals(person.getLastName()) && temp.getFirstName().equals(person.getFirstName())) {
                alreadyExist.set(true);
            }
        });

        if (alreadyExist.getAcquire() == true) return false;
        else return persons.add(person);
    }

    /**
     * update a person
     * return false if the person not updated
     *
     * @param person person
     * @return boolean
     * @see Persons
     */
    @Override
    public boolean update(Persons person) {
        LOGGER.info("Processing to update a person");
        if (deleteByFirstAndLastName(person.getFirstName(), person.getLastName())) {
            return persons.add(person);
        } else return false;

    }

    /**
     * find a person by first and last name
     *
     * @param firstName first name
     * @param lastName  last name
     * @return person
     * @see Persons
     */
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

    /**
     * delete a person by first and last name
     * return false if the person not deleted
     *
     * @param firstName first name
     * @param lastName  last name
     * @return boolean
     */
    @Override
    public boolean deleteByFirstAndLastName(final String firstName, final String lastName) {
        LOGGER.info("Processing to delete a person by first and last name");

        Persons result = findByFirstAndLastName(firstName, lastName);
        if (result == null) return false;
        return persons.remove(result);


    }

    /**
     * get a list of all persons by address
     *
     * @param address address
     * @return list of persons
     * @see Persons
     */
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

    /**
     * find a person by address
     *
     * @param address address
     * @return person
     * @see Persons
     */
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

    /**
     * find a list of persons by first and last name
     *
     * @param firstName first name
     * @param lastName  last name
     * @return list of persons
     * @see Persons
     */
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

    /**
     * get a list of persons by city
     *
     * @param city city
     * @return list of persons
     * @see Persons
     */
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
