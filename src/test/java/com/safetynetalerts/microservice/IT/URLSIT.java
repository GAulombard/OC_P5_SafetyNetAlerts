package com.safetynetalerts.microservice.IT;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class URLSIT {

    @Autowired
    public MockMvc mockMvc;

    @Test
    void getListOfPersonsCoveredByStationNumberIT() throws Exception {
        mockMvc.perform(get("/firestation?stationNumber=2")).andExpect(status().isOk()).andExpect(content().string(containsString("firstName2")));
    }

    @Test
    void getListOfChildrenByAddressIT() throws Exception {
        mockMvc.perform(get("/childAlert?address=address2")).andExpect(status().isOk()).andExpect(content().string(containsString("firstName2")));
    }

    @Test
    void getAllPhoneByStationNumberIT() throws Exception {
        mockMvc.perform(get("/phoneAlert?firestation=2")).andExpect(status().isOk()).andExpect(content().string(containsString("098-765-4321")));
    }

    @Test
    void getPersonsByAddressAndStationIT() throws Exception {
        mockMvc.perform(get("/fire?address=address2")).andExpect(status().isOk()).andExpect(content().string(containsString("098-765-4321")));
    }

    @Test
    void getHomesByStationsNumberIT() throws Exception {
        mockMvc.perform(get("/flood/stations?stations=1")).andExpect(status().isOk()).andExpect(content().string(containsString("allergie1")));
    }

    @Test
    void getAllInformationByFirstAndLastNameIT() throws Exception {
        mockMvc.perform(get("/personInfo?firstName=firstName&lastName=lastName")).andExpect(status().isOk()).andExpect(content().string(containsString("medication1:300mg")));
    }

    @Test
    void getAllEmailByCityIT() throws Exception {
        mockMvc.perform(get("/communityEmail?city=city")).andExpect(status().isOk()).andExpect(content().string(containsString("mail@email.com")));
    }
}
