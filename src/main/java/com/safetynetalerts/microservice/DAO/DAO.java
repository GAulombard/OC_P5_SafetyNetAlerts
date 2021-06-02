package com.safetynetalerts.microservice.DAO;

import com.safetynetalerts.microservice.model.FireStations;
import com.safetynetalerts.microservice.model.Persons;

import java.io.IOException;
import java.util.Set;

public interface DAO<O> {

    boolean save(O o);

    Set<O> findAll() throws IOException;

    boolean update(O o);


}
