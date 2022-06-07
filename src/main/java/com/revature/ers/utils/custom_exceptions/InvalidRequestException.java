package com.revature.ers.utils.custom_exceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(){
        super();
    }
    public InvalidRequestException(String message){
        super(message);
    }
}
