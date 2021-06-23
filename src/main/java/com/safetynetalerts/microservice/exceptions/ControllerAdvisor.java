package com.safetynetalerts.microservice.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/***
 * details for ControllerAdvisor
 */
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    /***
     * Not Found Exception handler
     * @see NotFoundException
     * @param ex exception
     * @param request request
     * @return response
     */
    @ExceptionHandler(value = { NotFoundException.class })
    public ResponseEntity<Object> handleNotFound(NotFoundException ex, HttpServletRequest request) {

        Map<String, Object> bodyOfResponse = new LinkedHashMap<>();
        bodyOfResponse.put("timestamp", LocalDateTime.now());
        bodyOfResponse.put("status", HttpStatus.NOT_FOUND.value());
        bodyOfResponse.put("error", "Not Found");
        bodyOfResponse.put("message", "Element not found");
        bodyOfResponse.put("path", request.getRequestURL().toString());

        return new ResponseEntity<>(bodyOfResponse,HttpStatus.NOT_FOUND);
    }

    /***
     * Already exist exception handler
     * @see AlreadyExistException
     * @param ex exception
     * @param request request
     * @return response
     */
    @ExceptionHandler(value = { AlreadyExistException.class })
    public ResponseEntity<Object> handleAlreadyExist(AlreadyExistException ex, HttpServletRequest request) {

        Map<String, Object> bodyOfResponse = new LinkedHashMap<>();
        bodyOfResponse.put("timestamp", LocalDateTime.now());
        bodyOfResponse.put("status", HttpStatus.CONFLICT.value());
        bodyOfResponse.put("error", "Conflict");
        bodyOfResponse.put("message", "Element Already exist");
        bodyOfResponse.put("path", request.getRequestURL().toString());

        return new ResponseEntity<>(bodyOfResponse,HttpStatus.CONFLICT);
    }

}
