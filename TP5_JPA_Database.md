# 🔴 TP5 --- Base de données avec JPA

## Travailler avec JPA
-   Ajouter compléter les dépendences dans le fichier build.gradle (JPA, testcontainers, PostgreSQL)
```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.liquibase:liquibase-core'
    runtimeOnly 'org.postgresql:postgresql'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.springframework.boot:spring-boot-testcontainers'
    testImplementation 'org.testcontainers:junit-jupiter'
    testImplementation 'org.testcontainers:postgresql'
    testImplementation 'org.junit.platform:junit-platform-suite'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```
-   Puis ajouter la dépendence vers une base de donnée de votre choix (PostgreSQL recommandé pour ce TP avec les exemples).\
-   Ne pas hésiter à jeter à un oeil au [build.gradle](https://github.com/corentinbeuchet/test-driven-development) du projet [test-driven-development](https://github.com/corentinbeuchet/test-driven-development) et de vous inspirer du code.\
-   Il est possible de copier coller les fichiers de migration depuis ce projet, le dossier [src](src)
## 📄 Book.java

``` java
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;
    private String title;
    private String author;
    private int year;
}
```

------------------------------------------------------------------------

## 📄 Repository

``` java
public interface BookRepository extends JpaRepository<Book, Long> {
}
```

------------------------------------------------------------------------

## 📄 Service

``` java
// TODO 11 : Utiliser BookRepository au lieu de la liste en mémoire
```


## Reste à faire

## Exigences Fonctionnelles

-   Le projet doit fonctionner avec une base de donnée
-   Spring data JPA

## Contraintes Techniques

-   Avoir une base de donnée de test ou utiliser les "test containers" comme dans le projet [test-driven-development](https://github.com/corentinbeuchet/test-driven-development)
-   Utiliser JPA pour travailler avec les données