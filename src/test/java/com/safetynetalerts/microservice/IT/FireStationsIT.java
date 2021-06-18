package com.safetynetalerts.microservice.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.microservice.datasource.DataBaseTestService;
import com.safetynetalerts.microservice.model.FireStations;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FireStationsIT {

    @Autowired
    public MockMvc mockMvc;

    private static DataBaseTestService dataBaseTestService = new DataBaseTestService();

    @AfterEach
    void tearDown() {
        dataBaseTestService.clearDBTest();
        dataBaseTestService.restoreDBTest();
    }

    @Test
    void getFireStationsIT() throws Exception {
        mockMvc.perform(get("/firestations")).andExpect(status().isOk());
    }
    @Test
    void postFireStationIT() throws Exception {
        FireStations fireStation = new FireStations("address", 12);
        mockMvc.perform(post("/firestation")
                .content(new ObjectMapper().writeValueAsString(fireStation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void putFireStationIT() throws Exception {
        FireStations fireStation = new FireStations("address", 12);
        mockMvc.perform(put("/firestation")
                .content(new ObjectMapper().writeValueAsString(fireStation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteFireStationByNumberIT() throws Exception {
        int station = 1;
        mockMvc.perform(delete("/firestation/station/{station}", station))
                .andExpect(status().isOk());
    }

    @Test
    void deleteFireStationByAddressIT() throws Exception {
        String address = "address";
        mockMvc.perform(delete("/firestation/address/{address}", address))
                .andExpect(status().isOk());
    }

}
