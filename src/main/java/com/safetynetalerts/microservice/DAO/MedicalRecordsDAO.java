package com.safetynetalerts.microservice.DAO;

import com.safetynetalerts.microservice.model.DTO.MedicalBackgroundDTO;
import com.safetynetalerts.microservice.model.MedicalRecords;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordsDAO extends DAO<MedicalRecords> {

    MedicalRecords findByFirstAndLastName(String firstName, String lastName);

    boolean deleteByFirstAndLastName(String firstName, String lastName);

    MedicalBackgroundDTO getMedicalBackgroundByFirstAndLastName(String firstName, String lastName);
}
