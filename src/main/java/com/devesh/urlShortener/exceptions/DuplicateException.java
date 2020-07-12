package com.devesh.urlShortener.exceptions;

public class DuplicateException extends RuntimeException {
    public DuplicateException() { }

    public DuplicateException(String message) { super(message); }

    public DuplicateException(String message, Throwable cause) { super(message, cause); }
}

