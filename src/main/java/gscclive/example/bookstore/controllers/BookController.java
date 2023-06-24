package gscclive.example.bookstore.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gscclive.example.bookstore.entities.Book;
import gscclive.example.bookstore.repositories.BookRepository;
import gscclive.example.bookstore.service.BookService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/book", produces = "application/json")
@Slf4j
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookController {

    private final BookRepository bookRepo;
    private final BookService bookService;
    private final ObservationRegistry obsRegistry;

    public BookController(BookRepository bookRepo, BookService bookService, ObservationRegistry obsRegistry) {
        this.bookRepo = bookRepo;
        this.bookService = bookService;
        this.obsRegistry = obsRegistry;
    }

    @GetMapping("/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Book> get(@PathVariable String isbn) {
        log.info("Retrieving book isbn: {}", isbn);
        Assert.state(isbn.contains("-"), "Invalid isbn. Please enter a valid isbn.");
        return Observation.createNotStarted("getBookByIsbn", obsRegistry)
                .observe(() -> bookRepo.findById(isbn));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Book> getAll() {
        log.info("Retrieving all books..");
        return bookRepo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Book> post(@RequestBody Book book) {
        log.info("Creating new book..");
        return bookService.create(book);
    }

    @DeleteMapping("/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String isbn) {
        log.info("Removing book isbn: {}", isbn);
        Assert.state(isbn.contains("-"), "Invalid isbn. Please enter a valid isbn.");
        bookRepo.deleteById(isbn);
    }

}
