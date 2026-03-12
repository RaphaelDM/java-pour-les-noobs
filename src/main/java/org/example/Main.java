package org.example;

import org.example.domain.Book;
import org.example.service.Library;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        var cleanCodeBook = new Book(1, "Clean Code", "Robert Martin", 2008);
        var effectiveJavaBook = new Book(2, "Effective Java", "Joshua Bloch", 2018);

        // ajout de livre
        System.out.println("Ajout d'un livre");
        library.addBook(cleanCodeBook);
        library.addBook(effectiveJavaBook);

        // affichage des livres
        library.displayBooks();

        // supprimer un livre
        System.out.println("Suppression d'un livre");
        library.removeBook(cleanCodeBook);

        // affichage des livres restant
        library.displayBooks();

        // Rajout du livre supprimé
        library.addBook(cleanCodeBook);

        // récupérer les livres d'un auteur
        var booksFromJoshua = library.getBooksFromAuthor(effectiveJavaBook.getAuthor());
        System.out.println("Livres de Joshua Bloch : " + booksFromJoshua);

        // récupérer un livre par titre
        var foundBook = library.findBookByTitle("Clean Code");
        System.out.println("Livre trouvé : " + foundBook);

        // récupérer des livre par titre partiel
        var foundBooks = library.getBooksByTitle("java");
        System.out.println("Livres trouvés : " + foundBooks);

        // récupérer les livres d'un auteur
        var booksFromJoshuaFoundByIsbn = library.getBookByIsbn(effectiveJavaBook.getIbsn());
        System.out.println("Livre trouvé par ISBN : " + booksFromJoshuaFoundByIsbn);
    }
}
