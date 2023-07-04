package gscclive.example.bookstore.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

/**
 * Handle Global Exception for endpoints.
 * 
 * @author GCLIVE
 */
@Component
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandlersAdvice {

    /**
     * Handle book isbn exist exception to a readable format.
     * 
     * @param ex of BookIsbnAlreadyExistException
     * @return response exception back to requester
     */
    @ExceptionHandler(BookIsbnAlreadyExistException.class)
    public ResponseStatusException handleBookIsbnExistException(BookIsbnAlreadyExistException ex) {
        log.error(ex.getMessage());
        return new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
    }

    /**
     * Handle book isbn does not exist exception to a readable format.
     * 
     * @param ex of BookNotFoundException
     * @return response exception back to requester
     */
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseStatusException handleBookIsbnExistException(BookNotFoundException ex) {
        log.error(ex.getMessage());
        return new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    /**
     * Handle validation of book fields exception to a readable format.
     * 
     * @param ex of MethodArgumentNotValidException
     * @return response exception back to requester
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseStatusException handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
        log.error(errors.toString());
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
    }

    /**
     * Handle book constraint violation exception to a readable format.
     * 
     * @param ex of ConstraintViolationException
     * @return response exception back to requester
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseStatusException handleBookConstraintViolationException(ConstraintViolationException ex) {
        log.error(ex.getMessage());
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessageTemplate)
                .collect(Collectors.joining(", ")));
    }

    /**
     * Handle book illegal state exception to a readable format.
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseStatusException handleIllegalStateException(IllegalStateException ex) {
        log.error(ex.getMessage());
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
