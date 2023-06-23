package gscclive.example.bookstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Book isbn already exist exception.
 * 
 * @author GCLIVE
 */
public class BookIsbnAlreadyExistException extends ResponseStatusException {
    
    public BookIsbnAlreadyExistException(String isbn) {
        super(HttpStatus.CONFLICT, "Book isbn: " + isbn + " already exist in database.");
    }
}
