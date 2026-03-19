package ort.lyon.demo.api;

import ort.lyon.demo.domain.Book;
import ort.lyon.demo.domain.port.in.BookUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookUseCase bookService;

    public BookController(BookUseCase bookService) {
        this.bookService = bookService;
    }

    // GET /books?sortBy=title|author|isbn  (défaut : title)
    @GetMapping
    public List<Book> getBooks(@RequestParam(defaultValue = "title") String sortBy) {
        return switch (sortBy) {
            case "author" -> bookService.getBooksSortedByAuthor();
            case "isbn"   -> bookService.getBooksSortedByIsbn();
            default       -> bookService.getBooksSortedByTitle();
        };
    }

    // POST /books
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    // PUT /books/{isbn}
    @PutMapping("/{isbn}")
    public Book updateBook(@PathVariable int isbn, @RequestBody Book book) {
        return bookService.updateBook(isbn, book);
    }

    // DELETE /books/{isbn}
    @DeleteMapping("/{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int isbn) {
        bookService.removeBookByIsbn(isbn);
    }
}