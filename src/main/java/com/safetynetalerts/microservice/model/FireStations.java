package com.safetynetalerts.microservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;

@JsonTypeName("firestations")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FireStations {

    private String address;
    private int station;

    public FireStations(){
        super();
    }

    @JsonCreator
    public FireStations(@JsonProperty("address") final String address,@JsonProperty("station") final int station) {
        this.address = address;
        this.station = station;
    }

    public String getAddress() {
        return address;
    }

    public int getStation() {
        return station;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setStation(final int station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "[station= " + station + ", address= " + address + "]";
    }
}
