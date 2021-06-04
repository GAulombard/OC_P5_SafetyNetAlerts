package com.safetynetalerts.microservice.DAO.Implements;

import com.safetynetalerts.microservice.DAO.FireStationsDAO;
import com.safetynetalerts.microservice.datasource.DataBase;
import com.safetynetalerts.microservice.datasource.DataBaseManager;
import com.safetynetalerts.microservice.model.FireStations;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashSet;
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
    public Set<FireStations> findFireStationsByStationNumber(final int stationNumber) {
        Set<FireStations> result = new HashSet<>();
        fireStations.iterator().forEachRemaining((fireStation -> {
            if (fireStation.getStation() == stationNumber) {
                result.add(fireStation);
            }
        }));

        return result;
    }

    @Override
    public Set<FireStations> findFireStationsByAddress(final String stationAddress) {
        Set<FireStations> result = new HashSet<>();
        fireStations.iterator().forEachRemaining((fireStation -> {
            if (fireStation.getAddress().equals(stationAddress)) {
                result.add(fireStation);
            }
        }));

        return result;
    }

    @Override
    public boolean deleteFireStationsByNumber(final int stationNumber) {
        Set<FireStations> fireStationsToDelete = findFireStationsByStationNumber(stationNumber);
        return fireStations.removeAll(fireStationsToDelete);
    }

    @Override
    public boolean deleteFireStationsByAddress(final String stationAddress) {
        Set<FireStations> fireStationsToDelete = findFireStationsByAddress(stationAddress);
        return fireStations.removeAll(fireStationsToDelete);
    }

    @Override
    public boolean update(FireStations fireStation) {
        if(deleteFireStationsByAddress(fireStation.getAddress())) {
            return fireStations.add(fireStation);
        } else {
            return false;
        }
    }

    @Override
    public boolean save(FireStations fireStation) {
        return fireStations.add(fireStation);
    }

    @Override
    public boolean delete(final FireStations fireStation) {
        return fireStations.remove(fireStation);
    }

    @Override
    public Set<String> getListOfAllAddressByStationNumber(final int stationNumber) {
        Set<String> result = new HashSet<>();
        fireStations.iterator().forEachRemaining(fireStation -> {
            if(fireStation.getStation() == stationNumber) {
                result.add(fireStation.getAddress());
            }
        });

        return result;

    }
}
