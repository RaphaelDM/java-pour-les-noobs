# 🟢 TP3 --- Java Moderne (Streams)

## 🎯 Objectif

Remplacer la boucle par un Stream.

``` java
// TODO 8 : Utiliser un Stream pour rechercher un livre

books.stream()
     .filter(book -> book.getTitle().equals(title))
     .findFirst();
```

## Reste à faire

## Exigences Fonctionnelles

-   Retourner une liste de livres par ordre alphabétique du titre
-   Retourner une liste de livres par ordre alphabétique de l'auteur
-   Ajouter des tests unitaires pour les méthodes de tri

## Contraintes Techniques

-   Utilisation de la classe `Stream`
-   Utilisation de la méthode `sort()` et implémenter [l'interface Comparator](https://www.baeldung.com/java-comparator-comparable)

## Bonus

-   Implémentation d'un [Collector personnalisé](https://www.baeldung.com/java-collectors)
-   Moteur de filtrage générique utilisant [Predicate](https://www.baeldung.com/java-predicate-chain)