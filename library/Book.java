public class Book {

    private String title;
    private String author;
    private int year;
    private final String ISBN; 

    // Constructor
    public Book(String title, String author, int year, String ISBN) {
        if (ISBN == null || ISBN.isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }
        this.title = title;
        this.author = author;
        this.year = year;
        this.ISBN = ISBN;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getISBN() {
        return ISBN;
    }

    // Override equals() and hashCode()
    @Override
    public boolean equals(Object o) { // Deux livres sont considérés égaux s'ils ont le même ISBN
        if (this == o) return true; 
        if (o == null || getClass() != o.getClass()) return false; // Vérifier que l'objet est du même type
        Book book = (Book) o; // Comparer les ISBN
        return ISBN.equals(book.ISBN);
    }

    @Override
    public int hashCode() { // Utiliser uniquement l'ISBN pour le hashCode, car c'est un identifiant unique
        return ISBN.hashCode();
    }

    // Override toString() : de manière à afficher les détails du livre de manière lisible
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}