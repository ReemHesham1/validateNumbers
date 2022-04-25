package com.numbervalidator.exercise.exceptions;

public class InvalidCountryCodeException extends RuntimeException{
    public InvalidCountryCodeException(String message) {
        super(message);
    }
}
