package com.safetynetalerts.microservice.util;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynetalerts.microservice.model.FireStations;
import com.safetynetalerts.microservice.model.Persons;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class JSonManager {



    public static List<Persons> getListPersons() throws IOException {
        String filePath = "data/data.json";
        byte[] bytesFile = Files.readAllBytes(new File(filePath).toPath());
        JsonIterator iter = JsonIterator.parse(bytesFile);
        Any any = iter.readAny();

        Any personAny = any.get("persons");
        List<Persons> persons = new ArrayList<>();

        personAny.forEach(a -> persons.add(new Persons(a.get("firstName").toString(), a.get("lastName").toString(), a.get("phone").toString(), a.get("zip").toString(), a.get("address").toString(), a.get("city").toString(), a.get("email").toString())));
        persons.forEach(p -> System.out.println("Name : "+p.getFirstName()+" "+p.getLastName()+" ; Address : "+p.getAddress()+" "+p.getZip()+" "+p.getCity()+" ; Phone : "+p.getPhone()));

        return persons;
    }

    public static List<FireStations> getListFireStations() throws IOException {
        String filePath = "data/data.json";
        byte[] bytesFile = Files.readAllBytes(new File(filePath).toPath());
        JsonIterator iter = JsonIterator.parse(bytesFile);
        Any any = iter.readAny();

        Any fireStationsAny = any.get("firestations");
        List<FireStations> fireStations = new ArrayList<>();

        fireStationsAny.forEach(a -> fireStations.add(new FireStations(a.get("address").toString(), a.get("station").toString())));
        fireStations.forEach(p->System.out.println("Address : "+p.getAddress()+" ; Station : "+p.getStation()));

        return fireStations;
    }

}
