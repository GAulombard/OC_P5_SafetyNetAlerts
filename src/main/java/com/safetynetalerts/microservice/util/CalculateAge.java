package com.safetynetalerts.microservice.util;

import com.safetynetalerts.microservice.model.MedicalRecords;

import java.util.Date;

public class CalculateAge {


    public int calculate(final MedicalRecords medicalRecord) {

        Date actualDate = new Date();
        Date birthdate = medicalRecord.getBirthDate();

        int age = actualDate.getYear() - birthdate.getYear();

        return age;
    }


}
