package gscclive.example.bookstore.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import gscclive.example.bookstore.entities.Author;
import gscclive.example.bookstore.entities.Book;
import gscclive.example.bookstore.exceptions.BookIsbnAlreadyExistException;
import gscclive.example.bookstore.repositories.AuthorRepository;
import gscclive.example.bookstore.repositories.BookRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;

    public BookService(BookRepository bookRepo, AuthorRepository authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    public Optional<Book> create(Book book) throws BookIsbnAlreadyExistException {
        if (bookRepo.existsById(book.getIsbn())) {
            throw new BookIsbnAlreadyExistException(book.getIsbn());
        }
        return this.update(book);
    }

    @Transactional
    public Optional<Book> update(Book book) {
        Set<Author> listOfAuthors = book.getAuthors().stream()
                .filter(author -> !authorRepo.existsById(author.getId()))
                .filter(author -> !authorRepo.existsByName(author.getName()))
                .map(author -> {
                    log.info("Saving new author: {}", author.getName());
                    return author;
                })
                .map(authorRepo::save)
                .collect(Collectors.toSet());

        listOfAuthors.addAll(book.getAuthors().stream()
                .filter(author -> authorRepo.existsByName(author.getName()))
                .map(author -> authorRepo.findByName(author.getName()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet()));

        log.info("Saving book isbn: {}", book.getIsbn());
        book.setAuthors(listOfAuthors);
        return Optional.of(bookRepo.save(book));
    }
}
