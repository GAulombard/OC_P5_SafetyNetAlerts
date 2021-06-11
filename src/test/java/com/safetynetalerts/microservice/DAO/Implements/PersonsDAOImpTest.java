package com.safetynetalerts.microservice.DAO.Implements;

import com.safetynetalerts.microservice.datasource.DataBase;
import com.safetynetalerts.microservice.datasource.DataBaseManager;
import com.safetynetalerts.microservice.datasource.DataBaseTestService;
import com.safetynetalerts.microservice.model.Persons;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PersonsDAOImpTest {

    private static DataBaseTestService dataBaseTestService = new DataBaseTestService();
    private static DataBase dataBase;

    private PersonsDAOImp personsDAOImp;

    @BeforeAll
    static void setupBeforeAll() {
        dataBase = DataBaseManager.INSTANCE.getDataBase();
    }

    @BeforeEach
    void setupBeforeEach() {
        personsDAOImp = new PersonsDAOImp();
    }

    @AfterEach
    void tearDown() {
        dataBaseTestService.clearDBTest();
        dataBaseTestService.restoreDBTest();
    }

    @Test
    void findAllTest() throws IOException {
        assertEquals(2,personsDAOImp.findAll().size());
    }

    @Test
    void findByPhoneTest() throws IOException {
        assertEquals("123-456-7890",personsDAOImp.findByPhone("123-456-7890").getPhone());
    }

    @Test
    void findByPhoneTest_whenPhoneNotFound() throws IOException {
        assertNull(personsDAOImp.findByPhone("1234567890"));
    }

    @Test
    void saveTest() {
        Persons personToTest = new Persons("Jean","Michel","0123456","12345","address","city","email@email.com");
        assertTrue(personsDAOImp.save(personToTest));
        assertEquals(3,dataBase.getPersons().size());
    }

    @Test
    void saveTest_WhenPersonAlreadyExist() {
        Persons personToTest = new Persons("firstName","lastName","123-456-7890","123","address","city","mail@email.com");
        assertFalse(personsDAOImp.save(personToTest));
        assertEquals(2,dataBase.getPersons().size());
    }

    @Test
    void updateTest(){
        String newAddress = "newAddress";
        Persons personToTest = new Persons("firstName","lastName","123-456-7890","123",newAddress,"city","mail@email.com");
        assertTrue(personsDAOImp.update(personToTest));
    }

    @Test
    void updateTest_whenPersonNotFound(){
        Persons personToTest = new Persons("Jean","Michel","0123456","12345","address","city","email@email.com");
        assertFalse(personsDAOImp.update(personToTest));
    }

    @Test
    void findByFirstAndLastNameTest(){
        assertEquals("firstName",personsDAOImp.findByFirstAndLastName("firstName","lastName").getFirstName());
        assertEquals("lastName",personsDAOImp.findByFirstAndLastName("firstName","lastName").getLastName());
        assertEquals("123-456-7890",personsDAOImp.findByFirstAndLastName("firstName","lastName").getPhone());
        assertEquals("mail@email.com",personsDAOImp.findByFirstAndLastName("firstName","lastName").getEmail());
    }

    @Test
    void findByFirstAndLastNameTest_whenPersonNotFound(){
        Persons personToTest = new Persons("Jean","Michel","0123456","12345","address","city","email@email.com");
        assertNull(personsDAOImp.findByFirstAndLastName(personToTest.getFirstName(),personToTest.getLastName()));
    }

    @Test
    void deleteByFirstAndLastNameTest() {
        assertTrue(personsDAOImp.deleteByFirstAndLastName("firstName","lastName"));
        assertEquals(1,dataBase.getPersons().size());
    }

    @Test
    void deleteByFirstAndLastNameTest_whenPersonNotFound() {
        assertFalse(personsDAOImp.deleteByFirstAndLastName("jean","michel"));
        assertEquals(2,dataBase.getPersons().size());
    }

    @Test
    void getListOfAllPersonsByAddressTest(){
        assertEquals(1,personsDAOImp.getListOfAllPersonsByAddress("address").size());
        assertEquals(1,personsDAOImp.getListOfAllPersonsByAddress("address2").size());
    }

    @Test
    void findPersonsByAddressTest(){
        assertEquals(1,personsDAOImp.findPersonsByAddress("address").size());
        assertEquals(1,personsDAOImp.findPersonsByAddress("address2").size());
    }

    @Test
    void findAllByFirstAndLastNameTest() {
        assertEquals(1,personsDAOImp.findAllByFirstAndLastName("firstName","lastName").size());
        assertEquals(1,personsDAOImp.findAllByFirstAndLastName("firstName2","lastName2").size());
    }

    @Test
    void findAllByFirstAndLastNameTest_whenPersonNotFound() {
        assertNull(personsDAOImp.findAllByFirstAndLastName("jean","michel"));
    }

    @Test
    void getListOfAllPersonsByCityTest(){
        assertEquals(2,personsDAOImp.getListOfAllPersonsByCity("city").size());
    }

}
