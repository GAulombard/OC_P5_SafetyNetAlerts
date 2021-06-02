package com.safetynetalerts.microservice.DAO.Implements;

import com.safetynetalerts.microservice.DAO.FireStationsDAO;
import com.safetynetalerts.microservice.datasource.DataBase;
import com.safetynetalerts.microservice.datasource.DataBaseManager;
import com.safetynetalerts.microservice.model.FireStations;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Set;

@Repository
public class FireStationsDAOImp implements FireStationsDAO {

    private DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();
    private Set<FireStations> fireStations = dataBase.getFireStations();

    @Override
    public Set<FireStations> findAll() throws IOException {
        return fireStations;
    }

    @Override
    public boolean update(FireStations o) {
        return false;
    }

    @Override
    public boolean save(FireStations fireStation) {
        return  fireStations.add(fireStation);
    }

    @Override
    public boolean delete() {
        return false;
    }
}
