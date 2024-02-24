package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Conflict.")
public class ConflictException extends RuntimeException {
    public ConflictException() {
        super("Conflict.");
    }
    public ConflictException(String message) {
        super(message);
    }
}