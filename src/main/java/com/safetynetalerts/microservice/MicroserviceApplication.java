package com.safetynetalerts.microservice;

import com.safetynetalerts.microservice.datasource.DataBase;
import com.safetynetalerts.microservice.datasource.DataBaseManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MicroserviceApplication {

	public static void main(String[] args) throws IOException {

		DataBase dataBase = DataBaseManager.INSTANCE.getDataBase();

		SpringApplication.run(MicroserviceApplication.class, args);
		//System.out.println(dataBase.toString());

	}

}
