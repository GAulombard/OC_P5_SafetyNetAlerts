package com.safetynetalerts.microservice.DAO;

import com.safetynetalerts.microservice.model.FireStations;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface FireStationsDAO extends DAO<FireStations> {
    /**
     * find fire stations by fire stations number
     *
     * @param stationNumber station number
     * @return list of fire stations
     * @see FireStations
     */
    Set<FireStations> findFireStationsByStationNumber(int stationNumber);

    /**
     * find fire stations by address
     *
     * @param stationAddress address
     * @return a list of fire stations
     * @see FireStations
     */
    Set<FireStations> findFireStationsByAddress(String stationAddress);

    /**
     * find fire station number by address
     *
     * @param stationAddress address
     * @return station number
     */
    int findFireStationsNumberByAddress(String stationAddress);

    /**
     * delete a fire station by station number
     *
     * @param stationNumber station number
     * @return boolean
     */
    boolean deleteFireStationsByNumber(int stationNumber);

    /**
     * delete a fire station by address
     *
     * @param stationAddress address
     * @return boolean
     */
    boolean deleteFireStationsByAddress(String stationAddress);

    /**
     * delete a fire station
     *
     * @param fireStation fire station
     * @return boolean
     * @see FireStations
     */
    boolean delete(FireStations fireStation);

    /**
     * get a list of address by station number
     *
     * @param stationNumber station number
     * @return a list of address
     */
    Set<String> getListOfAllAddressByStationNumber(int stationNumber);

    /**
     * get address by station number
     *
     * @param stationNumber station number
     * @return a list of address
     */
    Set<String> findAddressByStationNumber(int stationNumber);
}
