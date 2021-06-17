package com.safetynetalerts.microservice.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.microservice.DAO.FireStationsDAO;
import com.safetynetalerts.microservice.DAO.MedicalRecordsDAO;
import com.safetynetalerts.microservice.DAO.PersonsDAO;
import com.safetynetalerts.microservice.model.FireStations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = URLSController.class)
public class URLSControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonsDAO personsDAO;

    @MockBean
    private MedicalRecordsDAO medicalRecordsDAO;

    @MockBean
    private FireStationsDAO fireStationsDAO;

    @Test
    void getListOfPersonsCoveredByStationNumberTest() throws Exception {
        int stationNumber = 1;
        mockMvc.perform(get("/firestation?stationNumber="+stationNumber)).andExpect(status().isOk());
    }

    /*
    @Test
    void getListOfPersonsCoveredByStationNumberTest_whenUnknown() throws Exception {
        int stationNumber = 12;
        mockMvc.perform(get("/firestation?stationNumber="+stationNumber)).andExpect(status().isNotFound());
    }
    */

    @Test
    void getListOfChildrenByAddressTest() throws Exception {
        String address = "address";
        mockMvc.perform(get("/childAlert?address="+address)).andExpect(status().isOk());
    }

    /*
    @Test
    void getListOfChildrenByAddressTest_whenUnknown() throws Exception {
        String address = "add";
        mockMvc.perform(get("/childAlert?address="+address)).andExpect(status().isNotFound());
    }
    */

    @Test
    void getAllPhoneByStationNumberTest() throws Exception {
        int stationNumber = 1;
        mockMvc.perform(get("/phoneAlert?firestation="+stationNumber)).andExpect(status().isOk());

    }

    /*
    @Test
    void getAllPhoneByStationNumberTest_whenUnknown() throws Exception {
        int stationNumber = 13;
        mockMvc.perform(get("/phoneAlert?firestation="+stationNumber)).andExpect(status().isNotFound());

    }
    */

    @Test
    void getPersonsByAddressAndStationTest() throws Exception {
        String address ="address";
        mockMvc.perform(get("/fire?address="+address)).andExpect(status().isOk());
    }

    /*
    @Test
    void getPersonsByAddressAndStationTest_whenNotFound() throws Exception {
        String address ="add";
        mockMvc.perform(get("/fire?address="+address)).andExpect(status().isNotFound());
    }

    */

    @Test
    void getHomesByStationsNumberTest() throws Exception {
        int stationNumber = 1;
        mockMvc.perform(get("/flood/stations?stations="+stationNumber)).andExpect(status().isOk());
    }

    /*
    @Test
    void getHomesByStationsNumberTest_whenUnknown() throws Exception {
        int stationNumber = 13;
        mockMvc.perform(get("/flood/stations?stations="+stationNumber)).andExpect(status().isNotFound());
    }

    */

    @Test
    void getAllInformationByFirstAndLastNameTest() throws Exception {
        String firstName="firstName", lastName="lastName";
        mockMvc.perform(get("/personInfo?firstName="+firstName+"&lastName="+lastName)).andExpect(status().isOk());
    }

    /*
    @Test
    void getAllInformationByFirstAndLastNameTest_whenUnknown() throws Exception {
        String firstName="first", lastName="Name";
        mockMvc.perform(get("/personInfo?firstName="+firstName+"&lastName="+lastName)).andExpect(status().isNotFound());
    }
    */

    @Test
    void getAllEmailByCityTest() throws Exception {
        String city = "city";
        mockMvc.perform(get("/communityEmail?city="+city)).andExpect(status().isOk());
    }

    /*
    @Test
    void getAllEmailByCityTest_whenUnknown() throws Exception {
        String city = "ville";
        mockMvc.perform(get("/communityEmail?city="+city)).andExpect(status().isNotFound());
    }
    */

}
