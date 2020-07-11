package com.ayshiktest.exception;

public class ConflictException extends Exception {
    private static final long serialVersionUID = -9079454849611061074L;

    public ConflictException() {
        super();
    }

    public ConflictException(final String message) {
        super(message);
    }
}