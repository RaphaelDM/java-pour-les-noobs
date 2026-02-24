# 🟢 TP1 --- Java Fondamental Guidé

## Structure fournie

library/ ├── Main.java\
├── Book.java\
├── Library.java

------------------------------------------------------------------------

## 📄 Book.java (à compléter)

``` java
public class Book {

    private String title;
    private String author;
    private int year;

    // TODO 1 : Créer un constructeur

    // TODO 2 : Créer les getters

    // TODO 3 : Redéfinir toString()
}
```

------------------------------------------------------------------------

## 📄 Library.java (à compléter)

``` java
import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books = new ArrayList<>();

    // TODO 4 : Ajouter un livre
    // TODO 5 : Afficher tous les livres
    // TODO 6 : Rechercher un livre par titre
}
```

------------------------------------------------------------------------

## 📄 Main.java

``` java
public class Main {

    public static void main(String[] args) {

        Library library = new Library();

        library.addBook(new Book("Clean Code", "Robert Martin", 2008));
        library.addBook(new Book("Effective Java", "Joshua Bloch", 2018));

        library.displayBooks();
    }
}
```

## Reste à faire

### Exigences Fonctionnelles

-   Rajouter l'identifiant : [ISBN](https://en.wikipedia.org/wiki/ISBN)
-   Ajouter et supprimer des livres
-   Rechercher par titre, auteur et ISBN
-   Garantir l'unicité de l'ISBN
-   L'ISBN doit être immuable après création

## Contraintes Techniques

-   Encapsulation stricte (aucun champ public)
-   Implémentation correcte de equals() et hashCode()
-   toString() pertinent
-   Protégrer la branche main sur le repo github (besoin d'une PULL REQUEST, pas besoin de review car c'est une TP solo !)