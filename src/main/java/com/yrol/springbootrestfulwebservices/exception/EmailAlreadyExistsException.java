package com.yrol.springbootrestfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom runtime exception class when email already exists
 * */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends RuntimeException {

    private String email;

    public EmailAlreadyExistsException(String email) {
        super(String.format("%s has already been taken.", email));
        this.email = email;
    }
}
