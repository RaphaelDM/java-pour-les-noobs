package ort.lyon.demo.client;

import ort.lyon.demo.domain.Book;
import ort.lyon.demo.domain.port.in.BookUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/books")
public class BookViewController {

    private final BookUseCase bookUseCase;

    public BookViewController(BookUseCase bookUseCase) {
        this.bookUseCase = bookUseCase;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookUseCase.getBooksSortedByTitle());
        model.addAttribute("newBook", new Book());
        return "books/list";
    }

    @PostMapping
    public String addBook(@ModelAttribute Book book) {
        bookUseCase.addBook(book);
        return "redirect:/ui/books";
    }

    @PostMapping("/{isbn}/edit")
    public String updateBook(@PathVariable int isbn, @ModelAttribute Book book) {
        bookUseCase.updateBook(isbn, book);
        return "redirect:/ui/books";
    }

    @PostMapping("/{isbn}/delete")
    public String deleteBook(@PathVariable int isbn) {
        bookUseCase.removeBookByIsbn(isbn);
        return "redirect:/ui/books";
    }
}
