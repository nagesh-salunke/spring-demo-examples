package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class RestrictedUserException extends RuntimeException {

    public RestrictedUserException(String message) {
        super(message);
    }

}
