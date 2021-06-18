package com.safetynetalerts.microservice.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FireStationsTest {

    @Test
    void setFireStationTest() {
        FireStations fireStation = new FireStations();
        fireStation.setStation(5);
        fireStation.setAddress("address5");
        assertEquals(5,fireStation.getStation());
        assertEquals("address5",fireStation.getAddress());
        assertEquals("station= 5, address= address5\n",fireStation.toString());
    }
}
