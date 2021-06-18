package com.safetynetalerts.microservice.util;

import com.safetynetalerts.microservice.DAO.Implements.FireStationsDAOImp;
import com.safetynetalerts.microservice.DAO.Implements.MedicalRecordsDAOImp;
import com.safetynetalerts.microservice.DAO.MedicalRecordsDAO;
import com.safetynetalerts.microservice.datasource.DataBase;
import com.safetynetalerts.microservice.datasource.DataBaseManager;
import com.safetynetalerts.microservice.datasource.DataBaseTestService;
import com.safetynetalerts.microservice.model.MedicalRecords;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
        //dataBaseTestService.clearDBTest();
        //dataBaseTestService.restoreDBTest();
    }

    @Test
    void CalculateAgeTest_whenAdult() {
        CalculateAge ageCalculation = new CalculateAge();
        Calendar calendar = new GregorianCalendar(1994,06,15);
        Date date = calendar.getTime();
        MedicalRecords medicalRecord = new MedicalRecords("firstName","lastName",date,null,null);
        int age = ageCalculation.calculate(medicalRecord);
        assertEquals(27,age);

    }

    @Test
    void CalculateAgeTest_whenChildren() {
        CalculateAge ageCalculation2 = new CalculateAge();
        Calendar calendar2 = new GregorianCalendar(2012,06,15);
        Date date2 = calendar2.getTime();
        MedicalRecords medicalRecord2 = new MedicalRecords("firstName2","lastName2",date2,null,null);
        int age2 = ageCalculation2.calculate(medicalRecord2);
        assertEquals(9,age2);
    }

}
