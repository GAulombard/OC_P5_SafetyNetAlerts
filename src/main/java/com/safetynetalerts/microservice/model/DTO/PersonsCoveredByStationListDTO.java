package com.safetynetalerts.microservice.model.DTO;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * details for PersonsCoveredByStationListDTO
 */
public class PersonsCoveredByStationListDTO {
    /**
     * nbr adults
     */
    private AtomicInteger nbrAdults;
    /**
     * nbr children
     */
    private AtomicInteger nbrChildren;
    /**
     * persons
     *
     * @see PersonInfoDTO
     */
    private Set<PersonInfoDTO> persons;

    /**
     * getter
     *
     * @return nbr adults
     */
    public AtomicInteger getNbrAdults() {
        return nbrAdults;
    }

    /**
     * setter
     *
     * @param nbrAdults nbr adults
     */
    public void setNbrAdults(AtomicInteger nbrAdults) {
        this.nbrAdults = nbrAdults;
    }

    /**
     * getter
     *
     * @return nbr children
     */
    public AtomicInteger getNbrChildren() {
        return nbrChildren;
    }

    /**
     * setter
     *
     * @param nbrChildren nbr children
     */
    public void setNbrChildren(AtomicInteger nbrChildren) {
        this.nbrChildren = nbrChildren;
    }

    /**
     * getter
     *
     * @return persons
     */
    public Set<PersonInfoDTO> getPersons() {
        return persons;
    }

    /**
     * setter
     *
     * @param persons person
     */
    public void setPersons(Set<PersonInfoDTO> persons) {
        this.persons = persons;
    }

    /**
     * define the toString() method
     *
     * @return
     */
    @Override
    public String toString() {
        return "\nnbr Adults: " + nbrAdults + "\n nbr Children: " + nbrChildren + "\n Persons list: \n" + persons.toString();
    }
}
