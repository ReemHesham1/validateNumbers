package com.numbervalidator.exercise.exceptions;

public class InvalidCountryNameException extends RuntimeException{
    public InvalidCountryNameException(String message) {
        super(message);
    }
}
