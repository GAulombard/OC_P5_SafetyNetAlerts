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

/**
 * details for FireStationsDAOImp
 */
@Repository
public class FireStationsDAOImp implements FireStationsDAO {
    /**
     * LOGGER
     *
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(FireStationsDAOImp.class);
    /**
     * data base
     *
     * @see DataBase
     */
    private DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();
    /**
     * fire stations
     *
     * @see FireStations
     */
    private Set<FireStations> fireStations = dataBase.getFireStations();

    /**
     * get a list of all fire stations
     *
     * @return list of fire stations
     * @throws IOException
     */
    @Override
    public Set<FireStations> findAll() throws IOException {
        LOGGER.info("Processing to find all fire stations");
        return fireStations;
    }

    /**
     * get a list of fire station by station number
     * return null if not fire stations found
     *
     * @param stationNumber station number
     * @return list of fire station
     */
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

    /**
     * get a list of fire stations by address
     * return null if no fire stations found
     *
     * @param stationAddress address
     * @return list of fire stations
     */
    @Override
    public Set<FireStations> findFireStationsByAddress(final String stationAddress) {
        LOGGER.info("Processing to find fire station by address");
        Set<FireStations> result = new HashSet<>();
        fireStations.iterator().forEachRemaining((fireStation -> {
            if (fireStation.getAddress().equals(stationAddress)) {
                result.add(fireStation);
            }
        }));

        if (result.isEmpty()) return null;
        return result;
    }

    /**
     * get the number of the fire stations matching the address
     *
     * @param stationAddress address
     * @return station number
     */
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

    /**
     * delete a fire station by station number
     * return false if fire station not deleted
     *
     * @param stationNumber station number
     * @return boolean
     */
    @Override
    public boolean deleteFireStationsByNumber(final int stationNumber) {
        LOGGER.info("Processing to delete fire station by station number");
        Set<FireStations> fireStationsToDelete = findFireStationsByStationNumber(stationNumber);
        if (fireStationsToDelete == null) return false;
        return fireStations.removeAll(fireStationsToDelete);
    }

    /**
     * delete a fire station by address
     * return false if fire station not deleted
     *
     * @param stationAddress address
     * @return boolean
     */
    @Override
    public boolean deleteFireStationsByAddress(final String stationAddress) {
        LOGGER.info("Processing to delete fire station by address");
        Set<FireStations> fireStationsToDelete = findFireStationsByAddress(stationAddress);
        if (fireStationsToDelete == null) return false;
        return fireStations.removeAll(fireStationsToDelete);
    }

    /**
     * update a fire station
     * <p>
     * return false if the fire station not deleted
     *
     * @param fireStation fire stations
     * @return boolean
     * @see FireStations
     */
    @Override
    public boolean update(FireStations fireStation) {
        LOGGER.info("Processing to update fire station");
        if (deleteFireStationsByAddress(fireStation.getAddress())) {
            return fireStations.add(fireStation);
        } else {
            return false;
        }
    }

    /**
     * save a new fire station
     * return false if the new fire station not saved
     *
     * @param fireStation fire station
     * @return boolean
     * @see FireStations
     */
    @Override
    public boolean save(FireStations fireStation) {

        LOGGER.info("Processing to save new fire station");
        AtomicBoolean alreadyExist = new AtomicBoolean(false);

        fireStations.iterator().forEachRemaining(temp -> {
            if (temp.getStation() == fireStation.getStation() && temp.getAddress().equals(fireStation.getAddress())) {
                alreadyExist.set(true);
            }
        });

        if (alreadyExist.getAcquire() == true) return false;
        return fireStations.add(fireStation);
    }

    /**
     * delete a fire station
     * return false if the fire station not deleted
     *
     * @param fireStation fire station
     * @return boolean
     */
    @Override
    public boolean delete(final FireStations fireStation) {
        LOGGER.info("Processing to delete fire station");
        return fireStations.remove(fireStation);
    }

    /**
     * get a list of all address by station number
     *
     * @param stationNumber station number
     * @return list of address
     */
    @Override
    public Set<String> getListOfAllAddressByStationNumber(final int stationNumber) {
        LOGGER.info("Processing to get a list of address by station number");
        Set<String> result = new HashSet<>();
        fireStations.iterator().forEachRemaining(fireStation -> {
            if (fireStation.getStation() == stationNumber) {
                result.add(fireStation.getAddress());
            }
        });

        if (result.isEmpty()) return null;
        return result;

    }

    /**
     * find address by station number
     *
     * @param stationNumber station number
     * @return list of address
     */
    @Override
    public Set<String> findAddressByStationNumber(final int stationNumber) {
        LOGGER.info("Processing to find address by station number");
        Set<String> result = new HashSet<>();
        fireStations.iterator().forEachRemaining((fireStation -> {
            if (fireStation.getStation() == stationNumber) {
                result.add(fireStation.getAddress());
            }
        }));
        if (result.isEmpty()) return null;
        return result;
    }
}
