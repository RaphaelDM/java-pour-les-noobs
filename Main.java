public class Main {

    public static void main(String[] args) {

        Library library = new Library();

        library.addBook(new Book("Clean Code", "Robert Martin", 2008, "9780132350884"));
        library.addBook(new Book("Effective Java", "Joshua Bloch", 2018, "9780134685991"));

        library.displayBooks();
    }
}
