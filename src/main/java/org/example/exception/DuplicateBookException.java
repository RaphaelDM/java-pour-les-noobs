package org.example.exception;

public class DuplicateBookException extends LibraryException {

    public DuplicateBookException(String message) {
        super(message);
    }
}
