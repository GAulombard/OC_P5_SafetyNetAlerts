package com.safetynetalerts.microservice.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.microservice.DAO.FireStationsDAO;
import com.safetynetalerts.microservice.model.FireStations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = FireStationsController.class)
public class FireStationsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationsDAO fireStationsDAO;

    @Test
    void showListFireStationsTest() throws Exception {
        mockMvc.perform(get("/firestations")).andExpect(status().isOk());
    }

    @Test
    void createFireStationTest() throws Exception {
        FireStations fireStation = new FireStations("address14",5);
        when(fireStationsDAO.save(any(FireStations.class))).thenReturn(true);
        mockMvc.perform(post("/firestation")
                .content(new ObjectMapper().writeValueAsString(fireStation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void createFireStationTest_whenAlreadyExist() throws Exception {
        FireStations fireStation = new FireStations("address16",8);
        when(fireStationsDAO.save(any(FireStations.class))).thenReturn(false);
        mockMvc.perform(post("/firestation")
                .content(new ObjectMapper().writeValueAsString(fireStation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void createFireStationTest_whenInvalidArgument() throws Exception {
        FireStations fireStation = new FireStations(null,8);
        when(fireStationsDAO.save(any(FireStations.class))).thenReturn(false);
        mockMvc.perform(post("/firestation")
                .content(new ObjectMapper().writeValueAsString(fireStation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteByAddressTest() throws Exception {
        String address = "address";
        when(fireStationsDAO.deleteFireStationsByAddress(any(String.class))).thenReturn(true);
        mockMvc.perform(delete("/firestation/address/{address}",address))
                .andExpect(status().isOk());
    }

    @Test
    void deleteByAddressTest_whenUnknown() throws Exception {
        String address = "address4";
        when(fireStationsDAO.deleteFireStationsByAddress(any(String.class))).thenReturn(false);
        mockMvc.perform(delete("/firestation/address/{address}",address))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteByStationTest() throws Exception {
        int station = 2;
        when(fireStationsDAO.deleteFireStationsByNumber(any(int.class))).thenReturn(true);
        mockMvc.perform(delete("/firestation/station/{station}",station))
                .andExpect(status().isOk());
    }

    @Test
    void deleteByStationTest_whenUnknown() throws Exception {
        int station = 19;
        when(fireStationsDAO.deleteFireStationsByAddress(any(String.class))).thenReturn(false);
        mockMvc.perform(delete("/firestation/station/{station}",station))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateStationNumberTest() throws Exception {
        FireStations fireStation = new FireStations("address",24);
        when(fireStationsDAO.update(any(FireStations.class))).thenReturn(true);
        mockMvc.perform(put("/firestation")
                .content(new ObjectMapper().writeValueAsString(fireStation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateStationNumberTest_whenUnknown() throws Exception {
        FireStations fireStation = new FireStations("address456",27);
        when(fireStationsDAO.update(any(FireStations.class))).thenReturn(false);
        mockMvc.perform(put("/firestation")
                .content(new ObjectMapper().writeValueAsString(fireStation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateStationNumberTest_whenInvalidArgument() throws Exception {
        FireStations fireStation = new FireStations(null,27);
        when(fireStationsDAO.update(any(FireStations.class))).thenReturn(false);
        mockMvc.perform(put("/firestation")
                .content(new ObjectMapper().writeValueAsString(fireStation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getFireStationByStationNumberTest() throws Exception {
        int stationNumber = 1;
        FireStations fireStation = new FireStations("address",1);
        Set<FireStations> list = new HashSet<>();
        list.add(fireStation);
        when(fireStationsDAO.findFireStationsByStationNumber(any(int.class))).thenReturn(list);
        mockMvc.perform(get("/firestation/search/{stationNumber}",stationNumber)).andExpect(status().isOk());

    }

    @Test
    void getFireStationByStationNumberTest_whenUnknown() throws Exception {
        int stationNumber = 5;
        when(fireStationsDAO.findFireStationsByStationNumber(any(int.class))).thenReturn(null);
        mockMvc.perform(get("/firestation/search/{stationNumber}",stationNumber)).andExpect(status().isNotFound());
    }


}
