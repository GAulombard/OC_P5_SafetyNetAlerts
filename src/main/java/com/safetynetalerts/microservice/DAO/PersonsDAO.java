package com.safetynetalerts.microservice.DAO;

import com.safetynetalerts.microservice.model.Persons;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Set;

@Repository
public interface PersonsDAO extends DAO<Persons> {
    /**
     * find a person by phone
     *
     * @param phone phone
     * @return person
     * @throws IOException
     * @see Persons
     */
    Persons findByPhone(String phone) throws IOException;

    /**
     * find a person by first and last name
     *
     * @param firstName first name
     * @param lastName  last name
     * @return person
     * @see Persons
     */
    Persons findByFirstAndLastName(String firstName, String lastName);

    /**
     * delete a person by first and last name
     *
     * @param firstName first name
     * @param lastName  last name
     * @return boolean
     */
    boolean deleteByFirstAndLastName(final String firstName, final String lastName);

    /**
     * get a list of persons by address
     *
     * @param address address
     * @return list of persons
     * @see Persons
     */
    Set<Persons> getListOfAllPersonsByAddress(String address);

    /**
     * get a list of persons by address
     *
     * @param address address
     * @return list of persons
     * @see Persons
     */
    Set<Persons> findPersonsByAddress(String address);

    /**
     * get a list of persons by first and last name
     *
     * @param firstName first name
     * @param lastName  last name
     * @return list of persons
     * @see Persons
     */
    Set<Persons> findAllByFirstAndLastName(String firstName, String lastName);

    /**
     * get a list of persons by city
     *
     * @param city city
     * @return list of persons
     * @see Persons
     */
    Set<Persons> getListOfAllPersonsByCity(String city);
}
