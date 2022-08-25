package com.pvbank.portal.exceptions;

public class CustomBadRequestException extends RuntimeException{
    public CustomBadRequestException(String message){
        super(message);
    }
    public CustomBadRequestException(String message, Throwable cause){
        super(message,cause);
    }
}
