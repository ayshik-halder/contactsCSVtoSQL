package com.ayshiktest.exception;

public class CustomGeneralException extends Exception {
    private static final long serialVersionUID = -9079454849611061074L;

    public CustomGeneralException() {
        super();
    }

    public CustomGeneralException(final String message) {
        super(message);
    }
}
