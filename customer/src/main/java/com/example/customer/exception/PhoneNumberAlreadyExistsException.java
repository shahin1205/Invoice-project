package com.example.customer.exception;

public class PhoneNumberAlreadyExistsException extends RuntimeException {

    public PhoneNumberAlreadyExistsException(String message) {
        super(message);
    }

}
