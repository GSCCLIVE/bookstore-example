package gscclive.example.bookstore.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import gscclive.example.bookstore.entities.Author;
import gscclive.example.bookstore.entities.Book;
import gscclive.example.bookstore.exceptions.BookIsbnAlreadyExistException;
import gscclive.example.bookstore.exceptions.BookNotFoundException;
import gscclive.example.bookstore.repositories.BookRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { BookServiceContext.class })
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    public void beforeEach() {
        book = Book.builder()
                .isbn("100-1")
                .title("Unit testing")
                .authors(Set.of(Author.builder()
                        .name("GClive")
                        .birthday(LocalDate.now())
                        .build()))
                .year(1000)
                .price(100.5)
                .genre("Testing")
                .build();
    }

    @Test
    @DisplayName("Test create new book.")
    void testCreate() {
        // Prepare and Mock
        when(bookRepository.existsById(book.getIsbn())).thenReturn(false);
        when(bookRepository.save(any())).thenReturn(book);

        // Execute
        bookService.create(book);

        // Verify
        verify(bookRepository, atLeast(1)).save(any());
    }

    @Test
    @DisplayName("Test create new book that exist in db.")
    void testCreate1() {
        // Prepare and Mock
        when(bookRepository.existsById(book.getIsbn())).thenReturn(true);

        // Execute
        Assertions.assertThrows(BookIsbnAlreadyExistException.class, 
                () -> bookService.create(book), 
                "Expected to throw BookIsbnAlreadyExistException");

        // Verify
        verify(bookRepository, never()).save(any());
    }

    @Test
    @DisplayName("Test update book.")
    void testUpdate() {
        // Prepare and Mock
        book.setGenre("Updated booked");
        when(bookRepository.existsById(book.getIsbn())).thenReturn(true);
        when(bookRepository.save(any())).thenReturn(book);

        // Execute
        bookService.update(book);

        // Verify
        verify(bookRepository, atLeast(1)).save(any());
    }

    @Test
    @DisplayName("Test update book but does not exist in db.")
    void testUpdate1() {
        // Prepare and Mock
        when(bookRepository.existsById(book.getIsbn())).thenReturn(false);

        // Execute
        Assertions.assertThrows(BookNotFoundException.class, 
                () -> bookService.update(book), 
                "Expected to throw BookNotFoundException");

        // Verify
        verify(bookRepository, never()).save(any());
    }

    @Test
    @DisplayName("Delete book by isbn.")
    void testDelete() {
        // Prepare and Mock
        when(bookRepository.existsById(book.getIsbn())).thenReturn(true);

        // Execute
        bookService.delete(book.getIsbn());

        // Verify
        verify(bookRepository, atLeast(1)).deleteById(book.getIsbn());
    }

    @Test
    @DisplayName("Delete book by isbn which does not exist in db")
    void testDelete1() {
        // Prepare and Mock
        when(bookRepository.existsById(book.getIsbn())).thenReturn(false);

        // Execute
        Assertions.assertThrows(BookNotFoundException.class, 
                () -> bookService.delete(book.getIsbn()), 
                "Expected to throw BookNotFoundException");

        // Verify
        verify(bookRepository, never()).deleteById(book.getIsbn());
    }

    @Test
    @DisplayName("Test find all by author.")
    void testFindAllByAuthorsName() {
        // Prepare and Mock
        when(bookRepository.findAllByAuthorsName("GClive"))
                .thenReturn(List.of(book));

        // Execute
        bookService.findAllByAuthorsName("GClive");

        // Verify
        verify(bookRepository, atLeast(1))
                .findAllByAuthorsName("GClive");
    }

    @Test
    @DisplayName("Test find all by title.")
    void testFindAllByTitle() {
        // Prepare and Mock
        when(bookRepository.findAllByTitle(book.getTitle()))
                .thenReturn(List.of(book));

        // Execute
        bookService.findAllByAuthorsName(book.getTitle());

        // Verify
        verify(bookRepository, atLeast(1))
                .findAllByAuthorsName(book.getTitle());
    }

    @Test
    @DisplayName("Test find all by title or author.")
    void testFindAllByTitleOrAuthorsName() {
        // Prepare and Mock
        when(bookRepository.findAllByTitleOrAuthorsName(book.getTitle(), "GClive"))
                .thenReturn(List.of(book));

        // Execute
        bookService.findAllByTitleOrAuthorsName(book.getTitle(), "GClive");

        // Verify
        verify(bookRepository, atLeast(1))
                .findAllByTitleOrAuthorsName(book.getTitle(), "GClive");
    
    }


}
