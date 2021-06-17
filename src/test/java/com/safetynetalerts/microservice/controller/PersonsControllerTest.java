package com.safetynetalerts.microservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.microservice.DAO.PersonsDAO;
import com.safetynetalerts.microservice.model.Persons;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonsController.class)
public class PersonsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonsDAO personsDAO;

    @Test
    void showListPersonsTest() throws Exception {
        mockMvc.perform(get("/persons")).andExpect(status().isOk());
    }

    @Test
    void showPersonsByPhoneTest() throws Exception {
        String phone ="123-456-7890";
        mockMvc.perform(get("/person/{phone}",phone)).andExpect(status().isOk());
    }

    /*
    @Test
    void showPersonsByPhoneTest_whenUnknown() throws Exception {
        String phone ="123";
        mockMvc.perform(get("/person/{phone}",phone)).andExpect(status().isNotFound());
    }
    */

    @Test
    void createPersonTest() throws Exception {
        Persons person = new Persons("test","test","12456","12365","edrtdfg","city","mail@mail.com");
        when(personsDAO.save(any(Persons.class))).thenReturn(true);
        mockMvc.perform(post("/person")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void createPersonTest_whenAlreadyExist() throws Exception {
        Persons person = new Persons("firstName","lastName","12456","12365","edrtdfg","city","mail@mail.com");
        when(personsDAO.save(any(Persons.class))).thenReturn(false);
        mockMvc.perform(post("/person")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void createPersonTest_whenInvalidArgument() throws Exception {
        Persons person = new Persons(null,null,"12456","12365","edrtdfg","city","mail@mail.com");
        when(personsDAO.save(any(Persons.class))).thenReturn(false);
        mockMvc.perform(post("/person")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updatePersonTest() throws Exception {
        Persons person = new Persons("firstName","lastName","12456","12365","edrtdfg","city","mail@mail.com");
        when(personsDAO.update(any(Persons.class))).thenReturn(true);
        mockMvc.perform(put("/person")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updatePersonTest_whenNotFound() throws Exception {
        Persons person = new Persons("first","Name","12456","12365","edrtdfg","city","mail@mail.com");
        when(personsDAO.update(any(Persons.class))).thenReturn(false);
        mockMvc.perform(put("/person")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void updatePersonTest_whenInvalidArgument() throws Exception {
        Persons person = new Persons("firstName","lastName",null,null,null,null,null);
        when(personsDAO.update(any(Persons.class))).thenReturn(false);
        mockMvc.perform(put("/person")
                .content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deletePersonTest() throws Exception {
        String firstName ="firstName", lastName ="lastName";
        when(personsDAO.deleteByFirstAndLastName(any(String.class),any(String.class))).thenReturn(true);
        mockMvc.perform(delete("/person/{firstName}_{lastName}",firstName,lastName)).andExpect(status().isOk());
    }

    @Test
    void deletePersonTest_whenNotFound() throws Exception {
        String firstName ="first", lastName ="Name";
        when(personsDAO.deleteByFirstAndLastName(any(String.class),any(String.class))).thenReturn(false);
        mockMvc.perform(delete("/person/{firstName}_{lastName}",firstName,lastName)).andExpect(status().isNotFound());
    }

    @Test
    void getPersonsByFirstAndLastNameTest() throws Exception {
        String firstName ="firstName", lastName ="lastName";
        mockMvc.perform(get("/person/search/{firstName}_{lastName}",firstName,lastName)).andExpect(status().isOk());
    }

    /*
    @Test
    void getPersonsByFirstAndLastNameTest_whenNotFound() throws Exception {
        String firstName ="first", lastName ="Name";
        mockMvc.perform(get("/person/search/{firstName}_{lastName}",firstName,lastName)).andExpect(status().isNotFound());
    }
    */
}
