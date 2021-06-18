package com.safetynetalerts.microservice.DAO.Implements;

import com.safetynetalerts.microservice.DAO.MedicalRecordsDAO;
import com.safetynetalerts.microservice.datasource.DataBase;
import com.safetynetalerts.microservice.datasource.DataBaseManager;
import com.safetynetalerts.microservice.model.DTO.MedicalBackgroundDTO;
import com.safetynetalerts.microservice.model.MedicalRecords;
import com.safetynetalerts.microservice.model.Persons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@Repository
public class MedicalRecordsDAOImp implements MedicalRecordsDAO {

    private static final Logger LOGGER = LogManager.getLogger(MedicalRecordsDAOImp.class );
    private DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();
    private Set<MedicalRecords> medicalRecords = dataBase.getMedicalRecords();

    @Override
    public boolean save(MedicalRecords medicalRecord) {
        LOGGER.info("Processing to save new Medical record");
        AtomicBoolean alreadyExist = new AtomicBoolean(false);

        medicalRecords.iterator().forEachRemaining(temp -> {
            if(temp.getLastName().equals(medicalRecord.getLastName()) && temp.getFirstName().equals(medicalRecord.getFirstName())) {
                alreadyExist.set(true);
            }
        });

        if(alreadyExist.getAcquire() == true) return false;
        return medicalRecords.add(medicalRecord);
    }

    @Override
    public Set<MedicalRecords> findAll() throws IOException {
        LOGGER.info("Processing to find all Medical Records");
        return medicalRecords;
    }

    @Override
    public MedicalRecords findByFirstAndLastName(final String firstName, final String lastName) {
        LOGGER.info("Processing to find medical record by first and last name");
        Set<MedicalRecords> result = new HashSet<>();
        medicalRecords.iterator().forEachRemaining((medicalRecord) -> {
            if (medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName)) {
                result.add(medicalRecord);
            }
        });
        if(result.isEmpty()) return null;
        return result.iterator().next();
    }

    @Override
    public boolean deleteByFirstAndLastName(String firstName, String lastName) {
        LOGGER.info("Processing to delete medical record by first and last name");
        MedicalRecords result = findByFirstAndLastName(firstName, lastName);
        return medicalRecords.remove(result);
    }

    @Override
    public boolean update(MedicalRecords medicalRecord) {
        LOGGER.info("Processing to update medical record");
        if (deleteByFirstAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName())) {
            return medicalRecords.add(medicalRecord);
        } else return false;
    }

    @Override
    public MedicalBackgroundDTO getMedicalBackgroundByFirstAndLastName(final String firstName, final String lastName) {
        LOGGER.info("Processing to get medical background by first and last name");
        MedicalBackgroundDTO result = new MedicalBackgroundDTO();
        MedicalRecords medicalRecords = findByFirstAndLastName(firstName,lastName);

        result.setMedicationsList(medicalRecords.getMedications());
        result.setAllergiesList(medicalRecords.getAllergies());

        return result;
    }

}
