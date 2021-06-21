package com.safetynetalerts.microservice.DAO;

import java.io.IOException;
import java.util.Set;

public interface DAO<O> {
    /**
     * save a new object
     *
     * @param o
     * @return boolean
     */
    boolean save(O o);

    /**
     * get a list of all elements
     *
     * @return a list of elements
     * @throws IOException
     */
    Set<O> findAll() throws IOException;

    /**
     * update an existing object
     *
     * @param o
     * @return boolean
     */
    boolean update(O o);


}
