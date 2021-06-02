package com.safetynetalerts.microservice.DAO;

import com.safetynetalerts.microservice.model.FireStations;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Set;


@Repository
public interface FireStationsDAO extends DAO<FireStations>{

    boolean delete();

}
