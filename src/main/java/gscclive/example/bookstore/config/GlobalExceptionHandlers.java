package gscclive.example.bookstore.config;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import gscclive.example.bookstore.exceptions.BookIsbnAlreadyExistException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandlers {

    /**
     * Handle book isbn exist exception to a readable format.
     */
    @ExceptionHandler(BookIsbnAlreadyExistException.class)
    public ResponseStatusException handleBookIsbnExistException(BookIsbnAlreadyExistException ex) {
        log.error(ex.getMessage());
        return new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
    }

    /**
     * Handle book constraint violation exception to a readable format.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseStatusException handleBookConstraintViolationException(ConstraintViolationException ex) {
        log.error(ex.getMessage());
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessageTemplate)
                .collect(Collectors.joining(", ")));
    }
}
