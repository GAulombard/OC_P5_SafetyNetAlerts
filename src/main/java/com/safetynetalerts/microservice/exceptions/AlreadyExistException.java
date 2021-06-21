package com.safetynetalerts.microservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Element already exist")
public class AlreadyExistException extends RuntimeException {
    /**
     * Exception when an element already exist
     *
     * @param s message
     */
    public AlreadyExistException(final String s) {

        super(s);
    }
}
