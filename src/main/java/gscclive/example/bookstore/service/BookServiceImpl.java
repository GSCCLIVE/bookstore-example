package gscclive.example.bookstore.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gscclive.example.bookstore.entities.Book;
import gscclive.example.bookstore.exceptions.BookIsbnAlreadyExistException;
import gscclive.example.bookstore.exceptions.BookNotFoundException;
import gscclive.example.bookstore.repositories.BookRepository;

/**
 * Business logic of book service. 
 * 
 * @author GCLIVE
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book create(Book book) throws BookIsbnAlreadyExistException {
        if (bookRepository.existsById(book.getIsbn())) {
            throw new BookIsbnAlreadyExistException(book.getIsbn());
        }
        return this.save(book);
    }

    @Override
    public Book update(Book book) throws BookNotFoundException {
        if (!bookRepository.existsById(book.getIsbn())) {
            throw new BookNotFoundException(book.getIsbn());
        }
        return this.save(book);
    }

    @Override
    public Book save(Book book) {
        book.getAuthors().forEach(author -> author.setBook(book));
        return bookRepository.save(book);
    }

    @Override
    public void delete(String isbn) throws BookNotFoundException {
        if (!bookRepository.existsById(isbn)) {
            throw new BookNotFoundException(isbn);
        }
        bookRepository.deleteById(isbn);
    }

    @Override
    public Iterable<Book> findAllByTitleOrAuthorsName(String title, String author) {
        return bookRepository.findAllByTitleOrAuthorsName(title, author);
    }

    @Override
    public Iterable<Book> findAllByTitle(String title) {
        return bookRepository.findAllByTitle(title);
    }

    @Override
    public Iterable<Book> findAllByAuthorsName(String author) {
        return bookRepository.findAllByAuthorsName(author);
    }

}
