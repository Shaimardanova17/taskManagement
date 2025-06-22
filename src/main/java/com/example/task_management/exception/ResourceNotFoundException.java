package com.example.task_management.exception;

/**
 * Исключение, которое выбрасывается, когда запрошенный ресурс не найден.
 * Используется для обозначения ошибок с HTTP статусом 404 Not Found.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Конструктор с сообщением об ошибке.
     *
     * @param message подробное описание причины исключения
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
