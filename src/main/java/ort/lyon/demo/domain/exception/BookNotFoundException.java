package ort.lyon.demo.domain.exception;

public class BookNotFoundException extends LibraryException {

    public BookNotFoundException(String message) {
        super(message);
    }
}
