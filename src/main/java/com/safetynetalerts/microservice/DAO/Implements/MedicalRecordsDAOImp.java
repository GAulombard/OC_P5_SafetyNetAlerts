package com.safetynetalerts.microservice.DAO.Implements;

import com.safetynetalerts.microservice.DAO.MedicalRecordsDAO;
import com.safetynetalerts.microservice.datasource.DataBase;
import com.safetynetalerts.microservice.datasource.DataBaseManager;
import com.safetynetalerts.microservice.model.DTO.MedicalBackgroundDTO;
import com.safetynetalerts.microservice.model.MedicalRecords;
import com.safetynetalerts.microservice.model.Persons;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MedicalRecordsDAOImp implements MedicalRecordsDAO {

    private DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();
    private Set<MedicalRecords> medicalRecords = dataBase.getMedicalRecords();

    @Override
    public boolean save(MedicalRecords medicalRecord) {
        return medicalRecords.add(medicalRecord);
    }

    @Override
    public Set<MedicalRecords> findAll() throws IOException {
        return medicalRecords;
    }

    @Override
    public MedicalRecords findByFirstAndLastName(final String firstName, final String lastName) {
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
        MedicalRecords result = findByFirstAndLastName(firstName, lastName);
        return medicalRecords.remove(result);
    }

    @Override
    public boolean update(MedicalRecords medicalRecord) {
        if (deleteByFirstAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName())) {
            return medicalRecords.add(medicalRecord);
        } else return false;
    }

    @Override
    public MedicalBackgroundDTO getMedicalBackgroundByFirstAndLastName(final String firstName, final String lastName) {
        MedicalBackgroundDTO result = new MedicalBackgroundDTO();
        MedicalRecords medicalRecords = findByFirstAndLastName(firstName,lastName);

        result.setMedicationsList(medicalRecords.getMedications());
        result.setAllergiesList(medicalRecords.getAllergies());

        return result;
    }

}
