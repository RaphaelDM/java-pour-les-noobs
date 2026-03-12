# 🟡 TP4 --- Introduction à Spring Boot

## Travailler avec Spring Boot
 - Générer une application Spring Boot avec Gradle. Revoir comment celà a été fait avec le [automated-tests](https://github.com/corentinbeuchet/automated-tests).
 - Reprendre le code des TPs précédents et le rajouter dans ce nouveau projet spring boot

## 📄 BookController.java

``` java
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // TODO 9 : Créer un endpoint GET /books
}
```

------------------------------------------------------------------------

## 📄 BookService.java

``` java
@Service
public class BookService {

    private List<Book> books = new ArrayList<>();

    // TODO 10 : Retourner la liste des livres
}
```

## Reste à faire

## Exigences Fonctionnelles

-   Le service doit être capable de retourner une liste de livres triée par titre
-   Le service doit être capable de retourner une liste de livres triée par auteur
-   Le service doit être capable de retourner une liste de livres triée par ISBN
-   Le service doit permettre la suppression d'un livre par son ISBN
-   Le service doit permettre l'ajout d'un livre
-   Le controller doit implémenter les méthodes d'ajout et de suppression

## Contraintes Techniques

-   Ajout de tests d'intégration pour les 3 endpoints ajoutés au controller
-   Adapter les jobs de test pour qu'ils fonctionnent avec Spring Boot