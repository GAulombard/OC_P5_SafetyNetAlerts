package com.safetynetalerts.microservice.DAO;

import com.safetynetalerts.microservice.model.Persons;

import java.io.IOException;
import java.util.List;

public interface PersonsDAO {

    public List<Persons> findAll() throws IOException;

    Persons findByPhone(String phone) throws IOException;
}
