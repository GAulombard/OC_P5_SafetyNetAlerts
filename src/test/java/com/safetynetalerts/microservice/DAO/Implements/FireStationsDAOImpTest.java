package com.safetynetalerts.microservice.DAO.Implements;

import com.safetynetalerts.microservice.datasource.DataBase;
import com.safetynetalerts.microservice.datasource.DataBaseManager;
import com.safetynetalerts.microservice.datasource.DataBaseTestService;
import com.safetynetalerts.microservice.model.FireStations;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FireStationsDAOImpTest {

    private static DataBaseTestService dataBaseTestService = new DataBaseTestService();
    private static DataBase dataBase;

    private FireStationsDAOImp fireStationsDAOImp;

    @BeforeAll
    static void setupBeforeAll() {
        dataBase = DataBaseManager.INSTANCE.getDataBase();
    }

    @BeforeEach
    void setupBeforeEach() {
        fireStationsDAOImp = new FireStationsDAOImp();
    }

    @AfterEach
    void tearDown() {
        dataBaseTestService.clearDBTest();
        dataBaseTestService.restoreDBTest();
    }

    @Test
    void findAllTest() throws IOException {
        assertEquals(2,fireStationsDAOImp.findAll().size());
    }

    @Test
    void findFireStationsByStationNumberTest() {
        assertEquals(1,fireStationsDAOImp.findFireStationsByStationNumber(1).size());
        assertEquals(1,fireStationsDAOImp.findFireStationsByStationNumber(2).size());
    }

    @Test
    void findFireStationsByStationNumberTest_whenStationNotFound() {
        assertNull(fireStationsDAOImp.findFireStationsByStationNumber(3));
    }

    @Test
    void findFireStationsByAddressTest() {
        assertEquals(1,fireStationsDAOImp.findFireStationsByAddress("address").size());
        assertEquals(1,fireStationsDAOImp.findFireStationsByAddress("address2").size());
    }

    @Test
    void findFireStationsByAddressTest_whenStationNotFound() {
        assertNull(fireStationsDAOImp.findFireStationsByAddress("address3"));
    }

    @Test
    void findFireStationsNumberByAddressTest() {
        assertEquals(1,fireStationsDAOImp.findFireStationsNumberByAddress("address"));
        assertEquals(2,fireStationsDAOImp.findFireStationsNumberByAddress("address2"));
    }

    @Test
    void findFireStationsNumberByAddressTest_whenStationNotFound() {
        assertEquals(0,fireStationsDAOImp.findFireStationsNumberByAddress("address3"));
    }

    @Test
    void deleteFireStationsByNumberTest() {
        assertTrue(fireStationsDAOImp.deleteFireStationsByNumber(1));
        assertEquals(1,dataBase.getFireStations().size());
    }

    @Test
    void deleteFireStationsByNumberTest_whenStationNotFound() {
        assertFalse(fireStationsDAOImp.deleteFireStationsByNumber(3));
        assertEquals(2,dataBase.getFireStations().size());
    }

    @Test
    void deleteFireStationsByAddressTest() {
        assertTrue(fireStationsDAOImp.deleteFireStationsByAddress("address"));
        assertEquals(1,dataBase.getFireStations().size());
    }

    @Test
    void deleteFireStationsByAddressTest_whenStationNotFound() {
        assertFalse(fireStationsDAOImp.deleteFireStationsByAddress("address3"));
        assertEquals(2,dataBase.getFireStations().size());
    }

    @Test
    void updateTest(){
        FireStations fireStationToTest = new FireStations("address",3);
        assertTrue(fireStationsDAOImp.update(fireStationToTest));

    }

    @Test
    void updateTest_whenAddressNotFound(){
        FireStations fireStationToTest = new FireStations("address3",3);
        assertFalse(fireStationsDAOImp.update(fireStationToTest));

    }

    @Test
    void saveTest() {
        FireStations fireStationToTest = new FireStations("address3",3);
        assertTrue(fireStationsDAOImp.save(fireStationToTest));
        assertEquals(3,dataBase.getFireStations().size());
    }

    @Test
    void deleteTest() {
        FireStations fireStationToTest = new FireStations("address3",3);
        fireStationsDAOImp.save(fireStationToTest);
        assertTrue(fireStationsDAOImp.delete(fireStationToTest));
        assertEquals(2,dataBase.getFireStations().size());
    }

    @Test
    void getListOfAllAddressByStationNumberTest() {
        assertEquals(1,fireStationsDAOImp.getListOfAllAddressByStationNumber(1).size());
        assertEquals(1,fireStationsDAOImp.getListOfAllAddressByStationNumber(2).size());
    }

    @Test
    void getListOfAllAddressByStationNumberTest_whenStationNotFound() {
        assertNull(fireStationsDAOImp.getListOfAllAddressByStationNumber(3));
    }

    @Test
    void findAddressByStationNumberTest(){
        assertEquals(1,fireStationsDAOImp.findAddressByStationNumber(1).size());
        assertEquals(1,fireStationsDAOImp.findAddressByStationNumber(2).size());
    }

    @Test
    void findAddressByStationNumberTest_whenStationNumberNotFound(){
        assertNull(fireStationsDAOImp.findAddressByStationNumber(3));
    }

}
