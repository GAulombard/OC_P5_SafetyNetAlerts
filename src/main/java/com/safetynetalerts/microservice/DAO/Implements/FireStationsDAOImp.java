package com.safetynetalerts.microservice.DAO.Implements;

import com.safetynetalerts.microservice.DAO.FireStationsDAO;
import com.safetynetalerts.microservice.datasource.DataBase;
import com.safetynetalerts.microservice.datasource.DataBaseManager;
import com.safetynetalerts.microservice.model.FireStations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


@Repository
public class FireStationsDAOImp implements FireStationsDAO {

    private static final Logger LOGGER = LogManager.getLogger(FireStationsDAOImp.class);
    private DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();
    private Set<FireStations> fireStations = dataBase.getFireStations();

    @Override
    public Set<FireStations> findAll() throws IOException {
        LOGGER.info("Processing to find all fire stations");
        return fireStations;
    }

    @Override
    public Set<FireStations> findFireStationsByStationNumber(final int stationNumber) {
        LOGGER.info("Processing to find fire station by station number");
        Set<FireStations> result = new HashSet<>();
        fireStations.iterator().forEachRemaining((fireStation -> {
            if (fireStation.getStation() == stationNumber) {
                result.add(fireStation);
            }
        }));
        if (result.isEmpty()) return null;
        return result;
    }

    @Override
    public Set<FireStations> findFireStationsByAddress(final String stationAddress) {
        LOGGER.info("Processing to find fire station by address");
        Set<FireStations> result = new HashSet<>();
        fireStations.iterator().forEachRemaining((fireStation -> {
            if (fireStation.getAddress().equals(stationAddress)) {
                result.add(fireStation);
            }
        }));

        if(result.isEmpty()) return null;
        return result;
    }

    @Override
    public int findFireStationsNumberByAddress(final String stationAddress) {
        LOGGER.info("Processing to find fire station number by address");
        AtomicInteger result = new AtomicInteger();
        fireStations.iterator().forEachRemaining((fireStation -> {
            if (fireStation.getAddress().equals(stationAddress)) {
                result.set(fireStation.getStation());
            }
        }));
        return result.get();
    }

    @Override
    public boolean deleteFireStationsByNumber(final int stationNumber) {
        LOGGER.info("Processing to delete fire station by station number");
        Set<FireStations> fireStationsToDelete = findFireStationsByStationNumber(stationNumber);
        if (fireStationsToDelete==null) return false;
        return fireStations.removeAll(fireStationsToDelete);
    }

    @Override
    public boolean deleteFireStationsByAddress(final String stationAddress) {
        LOGGER.info("Processing to delete fire station by address");
        Set<FireStations> fireStationsToDelete = findFireStationsByAddress(stationAddress);
        if (fireStationsToDelete==null) return false;
        return fireStations.removeAll(fireStationsToDelete);
    }

    @Override
    public boolean update(FireStations fireStation) {
        LOGGER.info("Processing to update fire station");
        if(deleteFireStationsByAddress(fireStation.getAddress())) {
            return fireStations.add(fireStation);
        } else {
            return false;
        }
    }

    @Override
    public boolean save(FireStations fireStation) {

        LOGGER.info("Processing to save new fire station");
        AtomicBoolean alreadyExist = new AtomicBoolean(false);

        fireStations.iterator().forEachRemaining(temp -> {
            if(temp.getStation() == fireStation.getStation() && temp.getAddress().equals(fireStation.getAddress())) {
                alreadyExist.set(true);
            }
        });

        if(alreadyExist.getAcquire() == true) return false;
        return fireStations.add(fireStation);
    }

    @Override
    public boolean delete(final FireStations fireStation) {
        LOGGER.info("Processing to delete fire station");
        return fireStations.remove(fireStation);
    }

    @Override
    public Set<String> getListOfAllAddressByStationNumber(final int stationNumber) {
        LOGGER.info("Processing to get a list of address by station number");
        Set<String> result = new HashSet<>();
        fireStations.iterator().forEachRemaining(fireStation -> {
            if(fireStation.getStation() == stationNumber) {
                result.add(fireStation.getAddress());
            }
        });

        if(result.isEmpty()) return null;
        return result;

    }

    @Override
    public Set<String> findAddressByStationNumber(final int stationNumber) {
        LOGGER.info("Processing to find address by station number");
        Set<String> result = new HashSet<>();
        fireStations.iterator().forEachRemaining((fireStation -> {
            if (fireStation.getStation() == stationNumber) {
                result.add(fireStation.getAddress());
            }
        }));
        if(result.isEmpty()) return null;
        return result;
    }
}
