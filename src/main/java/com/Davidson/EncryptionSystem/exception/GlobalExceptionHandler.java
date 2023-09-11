package com.Davidson.EncryptionSystem.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidUserInputException(MethodArgumentNotValidException exception){
        Map<String, String> errorDetails = new HashMap<>();

        exception.getBindingResult().getFieldErrors()
                .forEach(fieldError ->
                        errorDetails.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return errorDetails;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleDataIntegrityViolationException(DataIntegrityViolationException exception){
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put(String.valueOf(exception.getRootCause()), "User Already Registered ");
        return errorDetails;
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleRequestNotSupportedException(HttpRequestMethodNotSupportedException exception){
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }


}
