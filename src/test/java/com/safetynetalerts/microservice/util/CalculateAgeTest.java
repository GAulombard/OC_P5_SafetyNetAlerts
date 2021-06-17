package com.safetynetalerts.microservice.util;

import com.safetynetalerts.microservice.DAO.Implements.FireStationsDAOImp;
import com.safetynetalerts.microservice.DAO.Implements.MedicalRecordsDAOImp;
import com.safetynetalerts.microservice.DAO.MedicalRecordsDAO;
import com.safetynetalerts.microservice.datasource.DataBase;
import com.safetynetalerts.microservice.datasource.DataBaseManager;
import com.safetynetalerts.microservice.datasource.DataBaseTestService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateAgeTest {

    private static DataBaseTestService dataBaseTestService = new DataBaseTestService();
    private static DataBase dataBase;

    private MedicalRecordsDAOImp medicalRecordsDAOImp;

    @BeforeAll
    static void setupBeforeAll() {
        dataBase = DataBaseManager.INSTANCE.getDataBase();
    }

    @BeforeEach
    void setupBeforeEach() {
        medicalRecordsDAOImp = new MedicalRecordsDAOImp();

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void CalculateAgeTest_whenAdult() {
        CalculateAge ageCalculation = new CalculateAge();
        int age = ageCalculation.calculate(medicalRecordsDAOImp.findByFirstAndLastName("firstName","lastName"));
        assertEquals(27,age);

    }

    @Test
    void CalculateAgeTest_whenChildren() {
        CalculateAge ageCalculation2 = new CalculateAge();
        int age2 = ageCalculation2.calculate(medicalRecordsDAOImp.findByFirstAndLastName("firstName2","lastName2"));
        assertEquals(9,age2);
    }

}
