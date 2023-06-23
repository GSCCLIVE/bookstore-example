package gscclive.example.bookstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Book isbn not found exception.
 * 
 * @author GCLIVE
 */
public class BookNotFoundException extends ResponseStatusException {

    public BookNotFoundException(String isbn) {
        super(HttpStatus.NOT_FOUND, "Book isbn: " + isbn + " not found.");
    }
}
