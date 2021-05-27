package com.safetynetalerts.microservice.DAO;

import com.safetynetalerts.microservice.model.Persons;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface PersonsDAO {

    public Set<Persons> findAll() throws IOException;

    Persons findByPhone(String phone) throws IOException;

    boolean save(Persons person);

    boolean update(Persons person);

}
