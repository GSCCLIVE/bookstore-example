package gscclive.example.bookstore.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gscclive.example.bookstore.entities.Author;
import gscclive.example.bookstore.repositories.AuthorRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/author", produces = "application/json")
@Slf4j
public class AuthorController {

    private final AuthorRepository authorRepo;

    public AuthorController(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }
    
    @GetMapping("/{id}")
    public Optional<Author> get(@PathVariable Integer id) {
        log.info("Retrieving author details by id: {}", id);
        return authorRepo.findById(id);
    }

    @GetMapping("/find/{name}")
    public Optional<Author> get(@PathVariable String name) {
        log.info("Retrieving author details by name: {}", name);
        return authorRepo.findByName(name);
    }

    @GetMapping
    public Iterable<Author> getAuthors() {
        log.info("Retrieving all authors..");
        return authorRepo.findAll();
    }
}
