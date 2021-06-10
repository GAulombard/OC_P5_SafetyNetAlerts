package com.safetynetalerts.microservice.model.DTO;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class PersonsCoveredByStationListDTO {

    private AtomicInteger nbrAdults;
    private AtomicInteger nbrChildren;
    private Set<PersonInfoDTO> persons;

    public AtomicInteger getNbrAdults() {
        return nbrAdults;
    }

    public void setNbrAdults(AtomicInteger nbrAdults) {
        this.nbrAdults = nbrAdults;
    }

    public AtomicInteger getNbrChildren() {
        return nbrChildren;
    }

    public void setNbrChildren(AtomicInteger nbrChildren) {
        this.nbrChildren = nbrChildren;
    }

    public Set<PersonInfoDTO> getPersons() {
        return persons;
    }

    public void setPersons(Set<PersonInfoDTO> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "\nnbr Adults: "+nbrAdults+"\n nbr Children: "+nbrChildren+"\n Persons list: \n"+persons.toString();
    }
}
