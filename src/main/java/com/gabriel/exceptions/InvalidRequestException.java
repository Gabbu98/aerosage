package com.gabriel.exceptions;

public class InvalidRequestException extends RuntimeException{

    public InvalidRequestException(final String message){
        super(message);
    }
}
