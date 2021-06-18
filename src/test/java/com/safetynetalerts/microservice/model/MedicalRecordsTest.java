package com.safetynetalerts.microservice.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedicalRecordsTest {

    @Test
    void setMedicalRecordTest() {
        MedicalRecords medicalRecord = new MedicalRecords();
        medicalRecord.setFirstName("first");
        medicalRecord.setLastName("last");
        medicalRecord.setBirthDate(null);
        medicalRecord.setMedications(null);
        medicalRecord.setAllergies(null);
        assertEquals("first",medicalRecord.getFirstName());
        assertEquals("last",medicalRecord.getLastName());
        assertEquals(null,medicalRecord.getBirthDate());
        assertEquals(null,medicalRecord.getMedications());
        assertEquals(null,medicalRecord.getAllergies());
        assertEquals("first last / birth date: null / medications: null / allergies: null\n",medicalRecord.toString());
    }

}
