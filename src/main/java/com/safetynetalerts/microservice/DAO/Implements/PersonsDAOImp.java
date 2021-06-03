package com.safetynetalerts.microservice.DAO.Implements;

import com.safetynetalerts.microservice.DAO.PersonsDAO;
import com.safetynetalerts.microservice.datasource.DataBase;
import com.safetynetalerts.microservice.datasource.DataBaseManager;
import com.safetynetalerts.microservice.model.Persons;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class PersonsDAOImp implements PersonsDAO {

    private DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();
    private Set<Persons> persons = dataBase.getPersons();

    @Override
    public Set<Persons> findAll() throws IOException {
        return persons;
    }

    @Override
    public Persons findByPhone(String phone) throws IOException {

        for (Persons person : persons) {
            if (person.getPhone().equals(phone)) {
                return person;
            }
        }
        return null;
    }


    //TODO: retourner l'element créé
    @Override
    public boolean save(final Persons person) {

        return persons.add(person);
    }

    //TODO: retourner l'element créé
    @Override
    public boolean update(Persons person) {
        if(deleteByFirstAndLastName(person.getFirstName(),person.getLastName())){
            return persons.add(person);
        }
        else return false;

    }

    @Override
    public Persons findByFirstAndLastName(final String firstName,final String lastName){
        Set<Persons> result = new HashSet<>();
        persons.iterator().forEachRemaining((person) -> { if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {result.add(person);}});

        return result.iterator().next();
    }

    @Override
    public boolean deleteByFirstAndLastName(final String firstName, final String lastName) {
        Persons result = findByFirstAndLastName(firstName,lastName);
        return persons.remove(result);
    }
}
