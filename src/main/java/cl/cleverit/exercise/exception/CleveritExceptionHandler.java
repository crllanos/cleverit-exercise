package cl.cleverit.exercise.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@Slf4j
@ControllerAdvice
public class CleveritExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CleveritException> handleEntityNotFoundException(EntityNotFoundException e) {
        log.error(String.format("Not found: %s.", e.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CleveritException.builder()
                        .message(e.getMessage())
                        .build());
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CleveritException> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(String.format("Bad request: %s.", e.getMessage()));
        return ResponseEntity.badRequest().body(CleveritException.builder()
                .message(e.getMessage())
                .build());
    }

}
