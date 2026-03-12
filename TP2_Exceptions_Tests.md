# 🟢 TP2 --- Exceptions & Tests

## 📄 Méthode à modifier

``` java
// TODO 7 : Lever une exception si le livre n'est pas trouvé
public Book findBookByTitle(String title) {
    return null;
}
```

------------------------------------------------------------------------

## 📄 Test fourni

``` java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    @Test
    void shouldFindBook() {
        Library library = new Library();
        library.addBook(new Book("Clean Code", "Robert Martin", 2008));

        Book book = library.findBookByTitle("Clean Code");

        assertEquals("Clean Code", book.getTitle());
    }
}
```

## Reste à faire

### Exigences Fonctionnelles

-   Remplacer List par Set/Map lorsque pertinent
-   Implémenter une hiérarchie d'exceptions :
    -   LibraryException
    -   BookNotFoundException
    -   DuplicateBookException
-   Changer la structure du projet et y ajouter Gradle

## Contraintes Techniques

-   JUnit 5 obligatoire
-   Compléter par des tests unitaires qui parraissent pertinents
-   Tests paramétrés requis [Documentation tests paramétrés](https://www.baeldung.com/parameterized-tests-junit-5)
-   Tests des cas limites indispensables
-   Ajouter la CI pour lancer les tests automatiquement
-   Bloquer le merge si la CI ne passe pas
