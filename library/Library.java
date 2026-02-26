import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books = new ArrayList<>();

    // Ajouter un livre
    public void addBook(Book book) {
        for (Book b : books) {
            if (b.getISBN().equals(book.getISBN())) {
                throw new IllegalArgumentException("A book with the same ISBN already exists.");
            }
        }
        books.add(book);
    }

    // Supprimer un livre
    public void removeBook(Book book) {
        books.remove(book);
    }

    // Afficher tous les livres
    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Rechercher un livre par titre
    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Rechercher un livre par auteur
    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    // Rechercher un livre par ISBN
    public Book findBookByISBN(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null;
    }
}