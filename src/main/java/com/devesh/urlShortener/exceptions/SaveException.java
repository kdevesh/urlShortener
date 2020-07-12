package com.devesh.urlShortener.exceptions;

public class SaveException extends RuntimeException {
    public SaveException() { }

    public SaveException(String message) { super(message); }

    public SaveException(String message, Throwable cause) { super(message, cause); }
}
