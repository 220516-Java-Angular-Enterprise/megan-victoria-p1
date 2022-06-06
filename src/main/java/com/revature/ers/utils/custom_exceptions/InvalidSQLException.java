package com.revature.ers.utils.custom_exceptions;

public class InvalidSQLException extends RuntimeException{
    public InvalidSQLException(String message) {
        super(message);
    }
}
