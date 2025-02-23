package org.botnicholas.projects.apigatewaymicro.exceptions;

public class SecretNotReadException extends Exception {
    private final String message;

    public SecretNotReadException(String message) {
        super(message);
        this.message = message;
    }
}
