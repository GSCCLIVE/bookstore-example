package gscclive.example.bookstore.repositories;

import org.springframework.data.repository.CrudRepository;

import gscclive.example.bookstore.entities.Book;

public interface BookRepository extends CrudRepository<Book, String> {

    Iterable<Book> findAllByTitleOrAuthorsName(String title, String author);

    Iterable<Book> findAllByTitle(String title);

    Iterable<Book> findAllByAuthorsName(String author);
}
