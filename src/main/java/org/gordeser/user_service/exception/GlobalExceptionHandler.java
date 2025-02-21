package org.gordeser.user_service.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("MethodArgumentNotValidException {}", exception.getMessage());

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(
                        error -> errors.put(error.getField(), error.getDefaultMessage())
                );

        return errors;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleArgumentTypeMismatch(MethodArgumentTypeMismatchException exception, HttpServletRequest request) {
        log.error("MethodArgumentTypeMismatchException {}", exception.getMessage());

        return ErrorResponse.builder()
                .url(request.getRequestURL().toString())
                .status(HttpStatus.BAD_REQUEST)
                .error("MethodArgumentTypeMismatchException")
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(DataValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDataValidationException(DataValidationException exception, HttpServletRequest request) {
        log.error("DataValidationException {}", exception.getMessage());

        return ErrorResponse.builder()
                .url(request.getRequestURL().toString())
                .status(HttpStatus.BAD_REQUEST)
                .error("DataValidationException")
                .message(exception.getMessage())
                .build();
    }
}
