package org.botnicholas.projects.authenticationservice.exceptions;

public class SecretNotReadException extends Exception {
    private final String message;

    public SecretNotReadException(String message) {
        super(message);
        this.message = message;
    }
}
