package com.bolt.earth.assignment.eventticketingsystem.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class ServiceExceptionHandler {

    @ExceptionHandler(CustomServiceException.class)
    public ResponseEntity<ErrorResponse> handleGenericCustomServiceException(Exception ex) {
        log.error("An exception occured :"+ ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorResponse.builder().errorCode(800001).errorMessage(ex.getMessage()).build());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleGenericRuntimeException(Exception ex) {
        log.error("An exception occured :"+ ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorResponse.builder().errorCode(800002).errorMessage(ex.getMessage()).build());
    }
}
