package com.safetynetalerts.microservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


@JsonTypeName("firestations")
@JsonIgnoreProperties(ignoreUnknown = true)

/**
 * Fire Stations details
 */
public class FireStations {
    /**
     * Fire station's address
     */
    @NotBlank(message = "address needed")
    private String address;
    /**
     * Fire station's number
     */
    @Positive(message = "station number should be positive")
    private int station;

    /**
     * default constructor
     */
    public FireStations() {
        super();
    }

    /**
     * Fire Stations constructor
     *
     * @param address station address
     * @param station station number
     */
    @JsonCreator
    public FireStations(@JsonProperty("address") final String address, @JsonProperty("station") final int station) {
        this.address = address;
        this.station = station;
    }

    /**
     * getter address
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * getter station
     *
     * @return station
     */
    public int getStation() {
        return station;
    }

    /**
     * setter address
     *
     * @param address
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    /**
     * setter station
     *
     * @param station
     */
    public void setStation(final int station) {
        this.station = station;
    }

    /**
     * Define toString()
     *
     * @return "station= " + station + ", address= " + address + "\n"
     */
    @Override
    public String toString() {
        return "station= " + station + ", address= " + address + "\n";
    }
}
