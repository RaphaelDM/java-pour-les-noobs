public class Main {

    public static void main(String[] args) {

        Library library = new Library();

        // Test d'ajout de livres
        library.addBook(new Book("Clean Code", "Robert Martin", 2008, "9780132350884"));
        library.addBook(new Book("Effective Java", "Joshua Bloch", 2018, "9780134685991"));
        library.addBook(new Book("The Pragmatic Programmer", "Andrew Hunt", 1999, "9780201616224"));
        library.displayBooks();

        // Test de la suppression
        library.removeBook(new Book("Clean Code", "Robert Martin", 2008, "9780132350884"));
        // Test de la recherche
        System.out.println("Recherche par titre : " + library.findBookByTitle("Effective Java"));
        System.out.println("Recherche par auteur : " + library.findBooksByAuthor("Joshua Bloch"));
        System.out.println("Recherche par ISBN : " + library.findBookByISBN("9780201616224"));

    }
}
