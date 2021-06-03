package com.safetynetalerts.microservice.DAO.Implements;

import com.safetynetalerts.microservice.DAO.MedicalRecordsDAO;
import com.safetynetalerts.microservice.datasource.DataBase;
import com.safetynetalerts.microservice.datasource.DataBaseManager;
import com.safetynetalerts.microservice.model.MedicalRecords;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Set;

@Repository
public class MedicalRecordsDAOImp implements MedicalRecordsDAO {

    private DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();
    private Set<MedicalRecords> medicalRecords = dataBase.getMedicalRecords();

    @Override
    public boolean save(MedicalRecords o) {
        return false;
    }

    @Override
    public Set<MedicalRecords> findAll() throws IOException {
        return medicalRecords;
    }

    @Override
    public boolean update(MedicalRecords o) {
        return false;
    }
}
