package com.safetynetalerts.microservice.util;

import com.safetynetalerts.microservice.model.MedicalRecords;

import java.util.Date;

/**
 * class to calculate age
 */
public class CalculateAge {

    /**
     * Calculate age for a person, from a medical record
     * @param medicalRecord medical record
     * @return age number
     */
    public int calculate(final MedicalRecords medicalRecord) {

        Date actualDate = new Date();
        Date birthdate = medicalRecord.getBirthDate();

        int age = actualDate.getYear() - birthdate.getYear();

        return age;
    }


}
