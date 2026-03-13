package ort.lyon.demo.domain.exception;

public class DuplicateBookException extends LibraryException {

    public DuplicateBookException(String message) {
        super(message);
    }
}
