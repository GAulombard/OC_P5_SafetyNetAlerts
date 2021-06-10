package com.safetynetalerts.microservice.DAO;

import com.safetynetalerts.microservice.model.FireStations;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface FireStationsDAO extends DAO<FireStations>{

    Set<FireStations> findFireStationsByStationNumber(int stationNumber);

    Set<FireStations> findFireStationsByAddress(String stationAddress);

    int findFireStationsNumberByAddress(String stationAddress);

    boolean deleteFireStationsByNumber(int stationNumber);

    boolean deleteFireStationsByAddress(String stationAddress);

    boolean delete(FireStations fireStation);

    Set<String> getListOfAllAddressByStationNumber(int stationNumber);

    Set<String> findAddressByStationNumber(int stationNumber);
}
