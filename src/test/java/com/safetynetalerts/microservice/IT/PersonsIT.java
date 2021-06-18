package com.safetynetalerts.microservice.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.microservice.datasource.DataBaseTestService;
import com.safetynetalerts.microservice.model.Persons;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonsIT {

    @Autowired
    public MockMvc mockMvc;

    private static DataBaseTestService dataBaseTestService = new DataBaseTestService();

    @AfterEach
    void tearDown() {
        dataBaseTestService.clearDBTest();
        dataBaseTestService.restoreDBTest();
    }

    @Test
    void getPersonsIT() throws Exception {
        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk());
    }

    @Test
    void postPersonIT() throws Exception {
        Persons person = new Persons("test", "test", "123456", "12345", "address", "test", "test@test.com");
        mockMvc.perform(post("/person")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void putPersonIT() throws Exception {
        Persons person = new Persons("firstName", "lastName", "456123", "123", "address", "city", "test@test.com");
        mockMvc.perform(put("/person")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deletePersonIT() throws Exception {
        String firstName = "firstName";
        String lastName = "lastName";
        mockMvc.perform(delete("/person/{firstName}_{lastName}", firstName, lastName))
                .andExpect(status().isOk());
    }
}