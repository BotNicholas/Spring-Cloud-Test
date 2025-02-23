package org.botnicholas.projects.authenticationservice.exceptions;

public class BadCredentialsException extends Exception {
    private final String message;

    public BadCredentialsException(String message) {
        super(message);
        this.message = message;
    }
}
