package com.example.task_management.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обработка ошибок валидации входящих DTO, отмеченных @Valid.
     * Возвращает статус 400 Bad Request и карту с полями и сообщениями об ошибках.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        // Собираем ошибки валидации по каждому полю
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors); // 400 Bad Request
    }

    /**
     * Обработка ошибок некорректного JSON или неправильного формата тела запроса.
     * Возвращает 400 с сообщением об ошибке.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidJson(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest()
                .body("Invalid JSON format: " + ex.getMostSpecificCause().getMessage()); // 400 Bad Request
    }

    /**
     * Обработка ошибок в параметрах запроса, таких как ConstraintViolationException и
     * MethodArgumentTypeMismatchException.
     * Возвращает 400 с пояснением ошибки.
     */
    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<String> handleConstraintViolations(Exception ex) {
        return ResponseEntity.badRequest()
                .body("Invalid request parameter: " + ex.getMessage()); // 400 Bad Request
    }

    /**
     * Обработка ситуации, когда ресурс (например, задача) не найден.
     * Возвращает 404 Not Found с сообщением из исключения.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage()); // 404 Not Found
    }

    /**
     * Универсальная обработка непредвиденных ошибок сервера.
     * Возвращает 500 Internal Server Error с сообщением об ошибке.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Unexpected server error: " + ex.getMessage()); // 500 Internal Server Error
    }
}
