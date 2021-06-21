package com.safetynetalerts.microservice.DAO;

import com.safetynetalerts.microservice.model.DTO.MedicalBackgroundDTO;
import com.safetynetalerts.microservice.model.MedicalRecords;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordsDAO extends DAO<MedicalRecords> {
    /**
     * find a medical record by first and last name
     *
     * @param firstName first name
     * @param lastName  last name
     * @return medical record
     * @see MedicalRecords
     */
    MedicalRecords findByFirstAndLastName(String firstName, String lastName);

    /**
     * delete a medical recorde by first and last name
     *
     * @param firstName first name
     * @param lastName  last name
     * @return boolean
     * @see MedicalRecords
     */
    boolean deleteByFirstAndLastName(String firstName, String lastName);

    /**
     * get a medical background by first and last name
     *
     * @param firstName first name
     * @param lastName  last name
     * @return medical background
     * @see MedicalBackgroundDTO
     */
    MedicalBackgroundDTO getMedicalBackgroundByFirstAndLastName(String firstName, String lastName);
}
