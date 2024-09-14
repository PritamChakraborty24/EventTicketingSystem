package com.bolt.earth.assignment.eventticketingsystem.exception;

import lombok.Builder;

@Builder
public class ErrorResponse {

    public int errorCode;
    public String errorMessage;
}
