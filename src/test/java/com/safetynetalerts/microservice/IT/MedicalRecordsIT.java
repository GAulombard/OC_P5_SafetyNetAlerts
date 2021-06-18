package com.safetynetalerts.microservice.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.microservice.datasource.DataBaseTestService;
import com.safetynetalerts.microservice.model.MedicalRecords;
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
public class MedicalRecordsIT {

    @Autowired
    public MockMvc mockMvc;

    private static DataBaseTestService dataBaseTestService = new DataBaseTestService();

    @AfterEach
    void tearDown() {
        dataBaseTestService.clearDBTest();
        dataBaseTestService.restoreDBTest();
    }

    @Test
    void getMedicalRecordsIT() throws Exception {
        mockMvc.perform(get("/medicalrecords")).
                andExpect(status().isOk());
    }

    @Test
    void postMedicalRecordIT() throws Exception {
        MedicalRecords medicalRecord = new MedicalRecords("test", "test", null, null, null);
        mockMvc.perform(post("/medicalrecord")
                .content(new ObjectMapper().writeValueAsString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
/*
    @Test
    void putMedicalRecordIT() throws Exception {
        MedicalRecords medicalRecord = new MedicalRecords("firstName", "lastName", null, null, null);
        mockMvc.perform(put("/medicalrecord")
                .content(new ObjectMapper().writeValueAsString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
*/
    @Test
    void deleteMedicalRecordIT() throws Exception {
        String firstName = "firstName";
        String lastName = "lastName";
        mockMvc.perform(delete("/medicalrecord/{firstName}_{lastName}", firstName, lastName))
                .andExpect(status().isOk());
    }
}