package com.example.task_management.dto;

import com.example.task_management.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

/** DTO-класс для передачи данных задачи между клиентом и сервером.
 Используется для REST API: запросы и ответы. Тут будут переменные, значения которых будем отправлять клиенту*/

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskDTO {

    /**
     *  UUID задачи
     */
    UUID id;

    /**
     * Заголовок задачи (обязательное поле, максимум 100 символов).
     */

    String title;

    /**
     * Описание задачи (необязательное поле).
     */
    String description;

    /**
     * Статус задачи (PENDING, IN_PROGRESS, COMPLETED).
     */
    Status status;

}

