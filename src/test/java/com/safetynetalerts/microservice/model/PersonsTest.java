package com.safetynetalerts.microservice.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonsTest {

    @Test
    void setPersonTest(){
        Persons person = new Persons();
        person.setFirstName("Jean");
        person.setLastName("Michel");
        person.setAddress("address");
        person.setCity("city");
        person.setPhone("789456");
        person.setZip("456");
        person.setEmail("jeanmichel@mail.com");
        assertEquals("Jean",person.getFirstName());
        assertEquals("Michel",person.getLastName());
        assertEquals("address",person.getAddress());
        assertEquals("city",person.getCity());
        assertEquals("789456",person.getPhone());
        assertEquals("456",person.getZip());
        assertEquals("jeanmichel@mail.com",person.getEmail());
        assertEquals("Jean Michel / address 456 city / email: jeanmichel@mail.com / phone: 789456\n",person.toString());
    }
}
