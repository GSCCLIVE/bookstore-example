package gscclive.example.bookstore.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gscclive.example.bookstore.entities.Book;
import gscclive.example.bookstore.service.BookService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * Book controller endpoint.
 * 
 * @author GCLIVE
 */
@RestController
@RequestMapping(path = "/book", produces = "application/json")
@Slf4j
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookController {

    private final BookService bookService;
    private final ObservationRegistry obsRegistry;

    /**
     * Constructor inject services.
     * 
     * @param bookService to inject
     * @param obsRegistry to inject
     */
    public BookController(BookService bookService, ObservationRegistry obsRegistry) {
        this.bookService = bookService;
        this.obsRegistry = obsRegistry;
    }

    /**
     * Find by title or by author.
     * 
     * @param title  if any
     * @param author if any
     * @return book result or empty list.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Book> findByTitleOrAuthor(
            @RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(required = false, defaultValue = "") String author) {

        if (!title.isEmpty() && !author.isEmpty()) {
            log.info("Finding book by title: {} and author: {}", title, author);
            return Observation.createNotStarted("findAllByTitleOrAuthorsName", obsRegistry)
                    .observe(() -> bookService.findAllByTitleOrAuthorsName(title, author));
        }

        if (!title.isEmpty()) {
            log.info("Finding book by title: {}", title);
            return Observation.createNotStarted("findAllByTitle", obsRegistry)
                    .observe(() -> bookService.findAllByTitle(title));
        }

        if (!author.isEmpty()) {
            log.info("Finding book by author: {}", author);
            return Observation.createNotStarted("findAllByAuthorsName", obsRegistry)
                    .observe(() -> bookService.findAllByAuthorsName(author));
        }

        return List.of();
    }

    /**
     * Create new book.
     * 
     * @param book with valid fields
     * @return book created
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book post(@Valid @RequestBody Book book) {
        log.info("Creating new book..");
        return Observation.createNotStarted("create", obsRegistry)
                .observe(() -> bookService.create(book));
    }

    /**
     * Update book info.
     * 
     * @param isbn of the book
     * @param book to update
     * @return book updated
     */
    @PostMapping("/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public Book update(@PathVariable String isbn, @RequestBody Book book) {
        log.info("Updating book isbn: {}", isbn);
        return Observation.createNotStarted("update", obsRegistry)
                .observe(() -> bookService.update(book));
    }

    /**
     * Delete book.
     * 
     * @param isbn of book
     */
    @DeleteMapping("/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String isbn) {
        log.info("Removing book isbn: {}", isbn);
        Assert.state(isbn.contains("-"), "Invalid isbn. Please enter a valid isbn.");
        Observation.createNotStarted("delete", obsRegistry)
                .observe(() -> bookService.delete(isbn));
    }

}
