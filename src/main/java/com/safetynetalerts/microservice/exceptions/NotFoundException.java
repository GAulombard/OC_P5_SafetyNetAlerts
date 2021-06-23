package com.safetynetalerts.microservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Element not Found")
public class NotFoundException extends RuntimeException {

    /**
     * Exception when an element is not found
     *
     * @param s message
     */
    public NotFoundException(String s) {

        super(s);

    }
}

