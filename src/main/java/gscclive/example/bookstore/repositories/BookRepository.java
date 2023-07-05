package gscclive.example.bookstore.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import gscclive.example.bookstore.entities.Book;

/**
 * Data access layer for book repository.
 * 
 * @author GCLIVE
 */
public interface BookRepository extends CrudRepository<Book, String> {

    Iterable<Book> findAllByTitleOrAuthorsName(String title, String author);

    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    Iterable<Book> findAllByTitle(String title);

    Iterable<Book> findAllByAuthorsName(String author);
}
