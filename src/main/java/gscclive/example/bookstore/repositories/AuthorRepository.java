package gscclive.example.bookstore.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import gscclive.example.bookstore.entities.Author;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
    
    boolean existsByName(String name); 

    Optional<Author> findByName(String name);
}
