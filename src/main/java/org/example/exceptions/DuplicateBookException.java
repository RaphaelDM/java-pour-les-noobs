package org.example.exceptions;

public class DuplicateBookException extends LibraryException {

    public DuplicateBookException(String message) {
        super(message);
    }
}
