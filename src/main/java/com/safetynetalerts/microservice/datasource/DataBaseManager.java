package com.safetynetalerts.microservice.datasource;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public enum DataBaseManager {

    INSTANCE;

    private DataBase dataBase;


    DataBaseManager() {
        ObjectMapper objectMapper = new ObjectMapper();
        URL url = getClass().getClassLoader().getResource("data.json");
        assert url != null;
        File source = new File(url.getFile());
        try {
            dataBase = objectMapper.readValue(source, DataBase.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    //TODO: update DataBase
    public void updateDataBase() {

    }
}
