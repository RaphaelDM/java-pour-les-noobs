package ort.lyon.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("Application démarrée !");
        System.out.println("Pour accéder à la documentation Swagger, rendez-vous sur : http://localhost:8080/swagger-ui.html");
        System.out.println("Pour accéder à l'API, rendez-vous sur : http://localhost:8080/books");
    }
}
