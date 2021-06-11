package com.safetynetalerts.microservice.DAO.Implements;

import com.safetynetalerts.microservice.datasource.DataBase;
import com.safetynetalerts.microservice.datasource.DataBaseManager;
import com.safetynetalerts.microservice.datasource.DataBaseTestService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

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
}
