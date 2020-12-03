package com.springrest.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //pre process the requests and post process the responses
public class SuperheroGlobalRESTExceptionHandler {
    //Add exception handler
    @ExceptionHandler
    // ResponseEntity<Type of Response body> methodName(Exception type to handle)
    public ResponseEntity<SuperheroErrorResponse> handleException(NotFoundException e){

        //create a SuperheroErrorResponse
        SuperheroErrorResponse errorResponse = new SuperheroErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());

        //return ResponseEntity
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler //to catch all exceptions
    public ResponseEntity<SuperheroErrorResponse> handleGenericException(Exception e){
        SuperheroErrorResponse errorResponse = new SuperheroErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
