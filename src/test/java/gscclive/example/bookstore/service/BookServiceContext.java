package gscclive.example.bookstore.service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import gscclive.example.bookstore.repositories.BookRepository;

@TestConfiguration
public class BookServiceContext {

    @MockBean
    public BookRepository bookRepository;

    @Bean
    public BookService bookService(BookRepository bookRepository) {
        return new BookServiceImpl(bookRepository);
    }
}
