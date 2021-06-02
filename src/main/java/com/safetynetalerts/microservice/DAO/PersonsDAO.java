package com.safetynetalerts.microservice.DAO;

import com.safetynetalerts.microservice.model.Persons;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Repository
public interface PersonsDAO {

    Set<Persons> findAll() throws IOException;

    Persons findByPhone(String phone) throws IOException;

    boolean save(Persons person);

    boolean update(Persons person);

    Persons findByFirstAndLastName(String firstName, String lastName);

    boolean delete(final String firstName, final String lastName);

}
