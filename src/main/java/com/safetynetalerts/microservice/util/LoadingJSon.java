package com.safetynetalerts.microservice.util;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynetalerts.microservice.model.Persons;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoadingJSon {

    public void loadJSon() throws IOException {
        String filePath = "data/data.json";
        byte[] bytesFile = Files.readAllBytes(new File(filePath).toPath());


        JsonIterator iter = JsonIterator.parse(bytesFile);
        Any any = iter.readAny();


        Any personAny = any.get("persons");
        List<Persons> persons = new ArrayList<>();
        personAny.forEach(a -> persons.add(new Persons(a.get("firstName").toString(), a.get("lastName").toString(), a.get("phone").toString(), a.get("zip").toString(), a.get("address").toString(), a.get("city").toString(), a.get("email").toString())));
        persons.forEach(p -> System.out.println(p.getFirstName().concat(p.getLastName().concat(p.getAddress().concat(p.getCity().concat(p.getPhone().concat(p.getZip())))))));

    }
}
