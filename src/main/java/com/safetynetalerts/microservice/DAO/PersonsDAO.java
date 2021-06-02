package com.safetynetalerts.microservice.DAO;

import com.safetynetalerts.microservice.model.Persons;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Set;

@Repository
public interface PersonsDAO extends DAO<Persons> {

    Persons findByPhone(String phone) throws IOException;

    Persons findByFirstAndLastName(String firstName, String lastName);

    boolean deleteByFirstAndLastName(final String firstName, final String lastName);

}
