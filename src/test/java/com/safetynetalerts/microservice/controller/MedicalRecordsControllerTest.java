package com.safetynetalerts.microservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.microservice.DAO.MedicalRecordsDAO;
import com.safetynetalerts.microservice.model.MedicalRecords;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MedicalRecordsController.class)
public class MedicalRecordsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordsDAO medicalRecordsDAO;

    @Test
    void showListMedicalRecordsTest() throws Exception {
        mockMvc.perform(get("/medicalrecords")).andExpect(status().isOk());
    }

/*
    @Test
    void createMedicalRecordTest() throws Exception {
        MedicalRecords medicalRecord = new MedicalRecords("firstName","Michel",null,null,null);
        when(medicalRecordsDAO.save(any(MedicalRecords.class))).thenReturn(true);
        mockMvc.perform(post("/medicalrecord")
                .content(new ObjectMapper().writeValueAsString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void createMedicalRecordTest_whenAlreadyExist() throws Exception {
        MedicalRecords medicalRecord = new MedicalRecords("firstName","lastName",null,null,null);
        when(medicalRecordsDAO.save(any(MedicalRecords.class))).thenReturn(false);
        mockMvc.perform(post("/medicalrecord")
                .content(new ObjectMapper().writeValueAsString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }
*/

    @Test
    void createMedicalRecordTest_whenInvalidArgument() throws Exception {
        MedicalRecords medicalRecord = new MedicalRecords(null,null,null,null,null);
        when(medicalRecordsDAO.save(any(MedicalRecords.class))).thenReturn(false);
        mockMvc.perform(post("/medicalrecord")
                .content(new ObjectMapper().writeValueAsString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteMedicalRecordTest() throws Exception {
        String firstName = "firstName", lastName = "lastName";
        when(medicalRecordsDAO.deleteByFirstAndLastName(any(String.class),any(String.class))).thenReturn(true);
        mockMvc.perform(delete("/medicalrecord/{firstName}_{lastName}",firstName,lastName)).andExpect(status().isOk());
    }

    @Test
    void deleteMedicalRecordTest_whenUnknown() throws Exception {
        String firstName = "poet", lastName = "poet";
        when(medicalRecordsDAO.deleteByFirstAndLastName(any(String.class),any(String.class))).thenReturn(false);
        mockMvc.perform(delete("/medicalrecord/{firstName}_{lastName}",firstName,lastName)).andExpect(status().isNotFound());
    }

/*
    @Test
    void updateMedicalRecordTest() throws Exception {
        List<String> medications = new ArrayList<>();
        medications.add("test");
        medications.add("test2");
        MedicalRecords medicalRecord = new MedicalRecords("firstName","lastName",null,medications,null);
        when(medicalRecordsDAO.update(any(MedicalRecords.class))).thenReturn(true);
        mockMvc.perform(put("/medicalrecord")
                .content(new ObjectMapper().writeValueAsString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateMedicalRecordTest_whenUnknown() throws Exception {
        List<String> medications = new ArrayList<>();
        medications.add("test");
        medications.add("test2");
        MedicalRecords medicalRecord = new MedicalRecords("first","Name",null,medications,null);
        when(medicalRecordsDAO.update(any(MedicalRecords.class))).thenReturn(false);
        mockMvc.perform(put("/medicalrecord")
                .content(new ObjectMapper().writeValueAsString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
 */

    @Test
    void updateMedicalRecordTest_whenInvalidArgument() throws Exception {
        List<String> medications = new ArrayList<>();
        medications.add("test");
        medications.add("test2");
        MedicalRecords medicalRecord = new MedicalRecords(null,"Name",null,medications,null);
        when(medicalRecordsDAO.update(any(MedicalRecords.class))).thenReturn(false);
        mockMvc.perform(put("/medicalrecord")
                .content(new ObjectMapper().writeValueAsString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    void getMedicalRecordsByFirstAndLastNameTest() throws Exception {
        String firstName = "firstName", lastName = "lastName";
        MedicalRecords medicalRecord = new MedicalRecords(firstName,lastName,null,null,null);
        when(medicalRecordsDAO.findByFirstAndLastName(any(String.class),any(String.class))).thenReturn(medicalRecord);
        mockMvc.perform(get("/medicalrecord/search/{firstName}_{lastName}",firstName,lastName)).andExpect(status().isOk());
    }


    @Test
    void getMedicalRecordsByFirstAndLastNameTest_whenUnknown() throws Exception {
        String firstName = "first", lastName = "Name";
        MedicalRecords medicalRecord = new MedicalRecords(firstName,lastName,null,null,null);
        when(medicalRecordsDAO.findByFirstAndLastName(any(String.class),any(String.class))).thenReturn(null);
        mockMvc.perform(get("/medicalrecord/search/{firstName}_{lastName}",firstName,lastName)).andExpect(status().isNotFound());
    }


}
