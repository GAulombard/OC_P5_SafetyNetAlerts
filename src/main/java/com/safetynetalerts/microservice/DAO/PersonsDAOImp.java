package com.safetynetalerts.microservice.DAO;

import com.safetynetalerts.microservice.datasource.DataBase;
import com.safetynetalerts.microservice.datasource.DataBaseManager;
import com.safetynetalerts.microservice.model.Persons;
import org.springframework.stereotype.Repository;

import java.io.IOException;
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

    @Override
    public boolean save(final Persons person) {
        return persons.add(person);
    }

    @Override
    public boolean update(Persons person) {
        return persons.add(person);
    }
}
