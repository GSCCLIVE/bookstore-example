package gscclive.example.bookstore.service;

import gscclive.example.bookstore.entities.Book;
import gscclive.example.bookstore.exceptions.BookIsbnAlreadyExistException;
import gscclive.example.bookstore.exceptions.BookNotFoundException;

/**
 * Book service interface.
 * 
 * @author GCLIVE
 */
public interface BookService {
    
    public Book create(Book book) throws BookIsbnAlreadyExistException;

    public Book update(Book book) throws BookNotFoundException;

    public Book save(Book book);

    public void delete(String isbn) throws BookNotFoundException;

    public Iterable<Book> findAllByTitleOrAuthorsName(String title, String author);

    public Iterable<Book> findAllByTitle(String title);

    public Iterable<Book> findAllByAuthorsName(String author);

}
