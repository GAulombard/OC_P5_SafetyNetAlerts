package com.safetynetalerts.microservice.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.microservice.DAO.FireStationsDAO;
import com.safetynetalerts.microservice.DAO.MedicalRecordsDAO;
import com.safetynetalerts.microservice.DAO.PersonsDAO;
import com.safetynetalerts.microservice.model.DTO.MedicalBackgroundDTO;
import com.safetynetalerts.microservice.model.FireStations;
import com.safetynetalerts.microservice.model.MedicalRecords;
import com.safetynetalerts.microservice.model.Persons;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.util.*;

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
        Set<String> coveredAddressList = new HashSet<>();
        coveredAddressList.add("address1");
        coveredAddressList.add("address2");
        when(fireStationsDAO.getListOfAllAddressByStationNumber(any(int.class))).thenReturn(coveredAddressList);

        Set<Persons> personsList = new HashSet<>();
        personsList.add(new Persons("jean","michel","456789","123","address1","city","mail1@mail.com"));
        personsList.add(new Persons("jean2","michel2","4567892","1232","address2","city","mail2@mail.com"));
        when(personsDAO.findPersonsByAddress(any(String.class))).thenReturn(personsList);

        Calendar calendar = new GregorianCalendar(1994,06,15);
        Date date = calendar.getTime();
        MedicalRecords medicalRecord1 = new MedicalRecords("jean","michel",date,null,null);
        when(medicalRecordsDAO.findByFirstAndLastName(any(String.class), any(String.class))).thenReturn(medicalRecord1);

        mockMvc.perform(get("/firestation?stationNumber="+stationNumber)).andExpect(status().isOk());
    }

    @Test
    void getListOfPersonsCoveredByStationNumberTest_whenChildren() throws Exception {
        int stationNumber = 1;
        Set<String> coveredAddressList = new HashSet<>();
        coveredAddressList.add("address1");
        coveredAddressList.add("address2");
        when(fireStationsDAO.getListOfAllAddressByStationNumber(any(int.class))).thenReturn(coveredAddressList);

        Set<Persons> personsList = new HashSet<>();
        personsList.add(new Persons("jean","michel","456789","123","address1","city","mail1@mail.com"));
        personsList.add(new Persons("jean2","michel2","4567892","1232","address2","city","mail2@mail.com"));
        when(personsDAO.findPersonsByAddress(any(String.class))).thenReturn(personsList);

        Calendar calendar = new GregorianCalendar(1994,06,15);
        Date date2 = calendar.getTime();
        MedicalRecords medicalRecord2 = new MedicalRecords("jean2","michel2",date2,null,null);
        when(medicalRecordsDAO.findByFirstAndLastName(any(String.class), any(String.class))).thenReturn(medicalRecord2);

        mockMvc.perform(get("/firestation?stationNumber="+stationNumber)).andExpect(status().isOk());
    }


    @Test
    void getListOfPersonsCoveredByStationNumberTest_whenUnknown() throws Exception {
        int stationNumber = 12;
        when(fireStationsDAO.getListOfAllAddressByStationNumber(any(int.class))).thenReturn(null);
        mockMvc.perform(get("/firestation?stationNumber="+stationNumber)).andExpect(status().isNotFound());
    }


    @Test
    void getListOfChildrenByAddressTest() throws Exception {
        String address = "address";
        Set<Persons> personsList = new HashSet<>();
        personsList.add(new Persons("jean","michel","456789","123","address","city","mail1@mail.com"));
        personsList.add(new Persons("jean2","michel2","4567892","123","address","city","mail2@mail.com"));
        when(personsDAO.findPersonsByAddress(any(String.class))).thenReturn(personsList);

        Calendar calendar = new GregorianCalendar(1994,06,15);
        Date date = calendar.getTime();
        MedicalRecords medicalRecord1 = new MedicalRecords("jean","michel",date,null,null);
        when(medicalRecordsDAO.findByFirstAndLastName(any(String.class), any(String.class))).thenReturn(medicalRecord1);

        mockMvc.perform(get("/childAlert?address="+address)).andExpect(status().isOk());
    }

    @Test
    void getListOfChildrenByAddressTest_whenChildren() throws Exception {
        String address = "address";
        Set<Persons> personsList = new HashSet<>();
        personsList.add(new Persons("jean","michel","456789","123","address","city","mail1@mail.com"));
        personsList.add(new Persons("jean2","michel2","4567892","123","address","city","mail2@mail.com"));
        when(personsDAO.findPersonsByAddress(any(String.class))).thenReturn(personsList);

        Calendar calendar = new GregorianCalendar(2012,06,15);
        Date date = calendar.getTime();
        MedicalRecords medicalRecord1 = new MedicalRecords("jean2","michel2",date,null,null);
        when(medicalRecordsDAO.findByFirstAndLastName(any(String.class), any(String.class))).thenReturn(medicalRecord1);

        mockMvc.perform(get("/childAlert?address="+address)).andExpect(status().isOk());
    }

    @Test
    void getListOfChildrenByAddressTest_whenUnknown() throws Exception {
        String address = "add";
        when(personsDAO.findPersonsByAddress(any(String.class))).thenReturn(null);
        mockMvc.perform(get("/childAlert?address="+address)).andExpect(status().isNotFound());
    }


    @Test
    void getAllPhoneByStationNumberTest() throws Exception {
        int stationNumber = 1;
        Set<String> address  = new HashSet<>();
        address.add("address1");
        address.add("address2");
        when(fireStationsDAO.getListOfAllAddressByStationNumber(any(int.class))).thenReturn(address);

        Set<Persons> personsList = new HashSet<>();
        personsList.add(new Persons("jean","michel","456789","123","address1","city","mail1@mail.com"));
        personsList.add(new Persons("jean2","michel2","4567892","123","address1","city","mail2@mail.com"));
        when(personsDAO.getListOfAllPersonsByAddress(any(String.class))).thenReturn(personsList);

        mockMvc.perform(get("/phoneAlert?firestation="+stationNumber)).andExpect(status().isOk());

    }


    @Test
    void getAllPhoneByStationNumberTest_whenUnknown() throws Exception {
        int stationNumber = 13;
        when(fireStationsDAO.getListOfAllAddressByStationNumber(any(int.class))).thenReturn(null);
        mockMvc.perform(get("/phoneAlert?firestation="+stationNumber)).andExpect(status().isNotFound());

    }


    @Test
    void getPersonsByAddressAndStationTest() throws Exception {
        String address ="address";
        Set<Persons> personsList = new HashSet<>();
        personsList.add(new Persons("jean","michel","456789","123",address,"city","mail1@mail.com"));
        personsList.add(new Persons("jean2","michel2","4567892","123",address,"city","mail2@mail.com"));
        when(personsDAO.getListOfAllPersonsByAddress(any(String.class))).thenReturn(personsList);

        Calendar calendar = new GregorianCalendar(2012,06,15);
        Date date = calendar.getTime();
        MedicalRecords medicalRecord1 = new MedicalRecords("jean2","michel2",date,null,null);
        when(medicalRecordsDAO.findByFirstAndLastName(any(String.class), any(String.class))).thenReturn(medicalRecord1);
        when(fireStationsDAO.findFireStationsNumberByAddress(any(String.class))).thenReturn(1);

        MedicalBackgroundDTO medicalBackgroundDTO = new MedicalBackgroundDTO();
        medicalBackgroundDTO.setMedicationsList(null);
        medicalBackgroundDTO.setAllergiesList(null);
        when(medicalRecordsDAO.getMedicalBackgroundByFirstAndLastName(any(String.class),any(String.class))).thenReturn(medicalBackgroundDTO);

        mockMvc.perform(get("/fire?address="+address)).andExpect(status().isOk());
    }


    @Test
    void getPersonsByAddressAndStationTest_whenNotFound() throws Exception {
        String address ="add";
        when(personsDAO.getListOfAllPersonsByAddress(any(String.class))).thenReturn(null);
        mockMvc.perform(get("/fire?address="+address)).andExpect(status().isNotFound());
    }

    @Test
    void getHomesByStationsNumberTest() throws Exception {
        int stationNumber = 1;
        Set<String> allAddressList = new HashSet<>();
        allAddressList.add("address1");
        allAddressList.add("address2");
        allAddressList.add("address3");
        when(fireStationsDAO.findAddressByStationNumber(any(int.class))).thenReturn(allAddressList);

        Set<Persons> personsAtAddress = new HashSet<>();
        personsAtAddress.add(new Persons("jean","michel","456789","123","address1","city","mail1@mail.com"));
        personsAtAddress.add(new Persons("jean2","michel2","4567892","123","address1","city","mail2@mail.com"));
        when(personsDAO.getListOfAllPersonsByAddress(any(String.class))).thenReturn(personsAtAddress);

        Calendar calendar = new GregorianCalendar(2012,06,15);
        Date date = calendar.getTime();
        MedicalRecords medicalRecord1 = new MedicalRecords("jean2","michel2",date,null,null);
        when(medicalRecordsDAO.findByFirstAndLastName(any(String.class), any(String.class))).thenReturn(medicalRecord1);

        MedicalBackgroundDTO medicalBackgroundDTO = new MedicalBackgroundDTO();
        medicalBackgroundDTO.setMedicationsList(null);
        medicalBackgroundDTO.setAllergiesList(null);
        when(medicalRecordsDAO.getMedicalBackgroundByFirstAndLastName(any(String.class),any(String.class))).thenReturn(medicalBackgroundDTO);

        mockMvc.perform(get("/flood/stations?stations="+stationNumber)).andExpect(status().isOk());
    }


    @Test
    void getHomesByStationsNumberTest_whenUnknown() throws Exception {
        int stationNumber = 13;
        when(fireStationsDAO.findAddressByStationNumber(any(int.class))).thenReturn(null);
        mockMvc.perform(get("/flood/stations?stations="+stationNumber)).andExpect(status().isNotFound());
    }



    @Test
    void getAllInformationByFirstAndLastNameTest() throws Exception {
        String firstName="firstName", lastName="lastName";
        Set<Persons> personsAtAddress = new HashSet<>();
        personsAtAddress.add(new Persons(firstName,lastName,"456789","123","address1","city","mail1@mail.com"));
        when(personsDAO.findAllByFirstAndLastName(any(String.class),any(String.class))).thenReturn(personsAtAddress);

        Calendar calendar = new GregorianCalendar(2012,06,15);
        Date date = calendar.getTime();
        MedicalRecords medicalRecord1 = new MedicalRecords(firstName,lastName,date,null,null);
        when(medicalRecordsDAO.findByFirstAndLastName(any(String.class), any(String.class))).thenReturn(medicalRecord1);

        MedicalBackgroundDTO medicalBackgroundDTO = new MedicalBackgroundDTO();
        medicalBackgroundDTO.setMedicationsList(null);
        medicalBackgroundDTO.setAllergiesList(null);
        when(medicalRecordsDAO.getMedicalBackgroundByFirstAndLastName(any(String.class),any(String.class))).thenReturn(medicalBackgroundDTO);

        mockMvc.perform(get("/personInfo?firstName="+firstName+"&lastName="+lastName)).andExpect(status().isOk());
    }


    @Test
    void getAllInformationByFirstAndLastNameTest_whenUnknown() throws Exception {
        String firstName="first", lastName="Name";
        when(personsDAO.findAllByFirstAndLastName(any(String.class),any(String.class))).thenReturn(null);
        mockMvc.perform(get("/personInfo?firstName="+firstName+"&lastName="+lastName)).andExpect(status().isNotFound());
    }


    @Test
    void getAllEmailByCityTest() throws Exception {
        String city = "city";
        Set<Persons> personsAtAddress = new HashSet<>();
        personsAtAddress.add(new Persons("jean","michel","456789","123","address1",city,"mail1@mail.com"));
        personsAtAddress.add(new Persons("jean2","michel2","4567892","123","address1",city,"mail2@mail.com"));
        when(personsDAO.getListOfAllPersonsByCity(any(String.class))).thenReturn(personsAtAddress);

        mockMvc.perform(get("/communityEmail?city="+city)).andExpect(status().isOk());
    }


    @Test
    void getAllEmailByCityTest_whenUnknown() throws Exception {
        String city = "ville";
        when(personsDAO.getListOfAllPersonsByCity(any(String.class))).thenReturn(null);
        mockMvc.perform(get("/communityEmail?city="+city)).andExpect(status().isNotFound());
    }


}
