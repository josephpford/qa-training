package learn.todos.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) throws Exception {
        // Allow Spring MVC to handle these exception types.
        if (ex instanceof HttpMessageNotReadableException || ex instanceof HttpMediaTypeNotSupportedException) {
            throw ex;
        }

        return ErrorResponse.build(
                String.format("Message: %s, Stack Trace: %s", ex.getMessage(), Arrays.toString(ex.getStackTrace())),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
