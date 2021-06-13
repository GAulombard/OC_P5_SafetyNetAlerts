package com.safetynetalerts.microservice.DAO.Implements;

import com.safetynetalerts.microservice.datasource.DataBase;
import com.safetynetalerts.microservice.datasource.DataBaseManager;
import com.safetynetalerts.microservice.datasource.DataBaseTestService;
import com.safetynetalerts.microservice.model.MedicalRecords;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class MedicalRecordsDAOImpTest {

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
        dataBaseTestService.clearDBTest();
        dataBaseTestService.restoreDBTest();
    }

    @Test
    void saveTest() {
        MedicalRecords medicalRecordToTest = new MedicalRecords("jean","michel",new Date(2006,06,12),null,null);
        assertTrue(medicalRecordsDAOImp.save(medicalRecordToTest));
        assertEquals(3,dataBase.getMedicalRecords().size());
    }

    @Test
    void findAllTest() throws IOException {
        assertEquals(2,medicalRecordsDAOImp.findAll().size());
    }

    @Test
    void findByFirstAndLastNameTest(){
        assertEquals("firstName",medicalRecordsDAOImp.findByFirstAndLastName("firstName","lastName").getFirstName());
        assertEquals("lastName",medicalRecordsDAOImp.findByFirstAndLastName("firstName","lastName").getLastName());
    }

    @Test
    void findByFirstAndLastNameTest_whenNotFound(){
        assertNull(medicalRecordsDAOImp.findByFirstAndLastName("firstName3","lastName"));
        assertNull(medicalRecordsDAOImp.findByFirstAndLastName("firstName","lastName3"));
    }

    @Test
    void deleteByFirstAndLastNameTest(){
        assertTrue(medicalRecordsDAOImp.deleteByFirstAndLastName("firstName","lastName"));
        assertEquals(1,dataBase.getMedicalRecords().size());
    }

    @Test
    void deleteByFirstAndLastNameTest_whenNotFound(){
        assertFalse(medicalRecordsDAOImp.deleteByFirstAndLastName("firstName4","lastName"));
        assertEquals(2,dataBase.getMedicalRecords().size());
    }

    @Test
    void updateTest(){
        MedicalRecords medicalRecordToTest = new MedicalRecords("firstName","lastName",new Date(2000,12,12),null,null);
        assertTrue(medicalRecordsDAOImp.update(medicalRecordToTest));
        assertNull(medicalRecordToTest.getMedications());
        assertNull(medicalRecordToTest.getAllergies());
    }

    @Test
    void updateTest_whenNotFound(){
        MedicalRecords medicalRecordToTest = new MedicalRecords("firtName","lastName",new Date(2000,12,12),null,null);
        assertFalse(medicalRecordsDAOImp.update(medicalRecordToTest));
    }

    @Test
    void getMedicalBackgroundByFirstAndLastNameTest() {
        assertEquals(2,medicalRecordsDAOImp.getMedicalBackgroundByFirstAndLastName("firstName","lastName").getMedicationsList().size());
        assertEquals(3,medicalRecordsDAOImp.getMedicalBackgroundByFirstAndLastName("firstName","lastName").getAllergiesList().size());
    }

}
