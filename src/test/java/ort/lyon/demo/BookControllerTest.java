package ort.lyon.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import ort.lyon.demo.domain.Book;
import ort.lyon.demo.domain.port.in.BookUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookUseCase bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // Réinitialise l'état entre les tests via un livre factice puis suppression
        // On passe par le service directement pour vider la bibliothèque
        try { bookService.removeBookByIsbn(1); } catch (Exception ignored) {}
        try { bookService.removeBookByIsbn(2); } catch (Exception ignored) {}
        try { bookService.removeBookByIsbn(3); } catch (Exception ignored) {}
    }

    // -----------------------------------------------------------------------
    // POST /books
    // -----------------------------------------------------------------------

    @Test
    void addBook_shouldReturn201() throws Exception {
        Book book = new Book(1, "Clean Code", "Robert Martin", 2008);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated());
    }

    @Test
    void addBook_duplicateIsbn_shouldReturn409() throws Exception {
        Book book = new Book(1, "Clean Code", "Robert Martin", 2008);
        bookService.addBook(book);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isConflict());
    }

    // -----------------------------------------------------------------------
    // DELETE /books/{isbn}
    // -----------------------------------------------------------------------

    @Test
    void deleteBook_shouldReturn204() throws Exception {
        bookService.addBook(new Book(2, "Effective Java", "Joshua Bloch", 2018));

        mockMvc.perform(delete("/books/2"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteBook_unknownIsbn_shouldReturn404() throws Exception {
        mockMvc.perform(delete("/books/999"))
                .andExpect(status().isNotFound());
    }

    // -----------------------------------------------------------------------
    // GET /books
    // -----------------------------------------------------------------------

    @Test
    void getBooks_sortedByTitle_shouldReturnOrderedList() throws Exception {
        bookService.addBook(new Book(1, "Refactoring", "Martin Fowler", 1999));
        bookService.addBook(new Book(2, "Clean Code", "Robert Martin", 2008));
        bookService.addBook(new Book(3, "Effective Java", "Joshua Bloch", 2018));

        mockMvc.perform(get("/books?sortBy=title"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].title", is("Clean Code")))
                .andExpect(jsonPath("$[1].title", is("Effective Java")))
                .andExpect(jsonPath("$[2].title", is("Refactoring")));
    }

    @Test
    void getBooks_sortedByAuthor_shouldReturnOrderedList() throws Exception {
        bookService.addBook(new Book(1, "Refactoring", "Martin Fowler", 1999));
        bookService.addBook(new Book(2, "Clean Code", "Robert Martin", 2008));
        bookService.addBook(new Book(3, "Effective Java", "Joshua Bloch", 2018));

        mockMvc.perform(get("/books?sortBy=author"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].author", is("Joshua Bloch")))
                .andExpect(jsonPath("$[1].author", is("Martin Fowler")))
                .andExpect(jsonPath("$[2].author", is("Robert Martin")));
    }

    @Test
    void getBooks_sortedByIsbn_shouldReturnOrderedList() throws Exception {
        bookService.addBook(new Book(3, "Effective Java", "Joshua Bloch", 2018));
        bookService.addBook(new Book(1, "Refactoring", "Martin Fowler", 1999));
        bookService.addBook(new Book(2, "Clean Code", "Robert Martin", 2008));

        mockMvc.perform(get("/books?sortBy=isbn"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].ibsn", is(1)))
                .andExpect(jsonPath("$[1].ibsn", is(2)))
                .andExpect(jsonPath("$[2].ibsn", is(3)));
    }
}
