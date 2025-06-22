package com.example.task_management.controllers;

import com.example.task_management.dto.TaskDTO;
import com.example.task_management.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.UUID;

@Tag(name = "main_methods")
@RestController
@RequestMapping("api/tasks") // Базовый путь для всех операций с задачами
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @Operation(
            summary = "Создание новой задачи",
            description = "Создаёт новую задачу",
            requestBody = @RequestBody(
                    description = "Данные задачи, которую необходимо создать",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TaskDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Задача успешно создана"),
                    @ApiResponse(responseCode = "400", description = "Невалидные данные")
            }
    )
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO taskDTO) {
        TaskDTO createdTask = taskService.createTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask); // 201 Created
    }

    @Operation(
            summary = "Получить задачу по ID",
            description = "Возвращает задачу по её ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Задача найдена"),
                    @ApiResponse(responseCode = "404", description = "Задача не найдена")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable UUID id) {
        TaskDTO taskId = taskService.findTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body(taskId); // 200 OK
    }

    @Operation(
            summary = "Обновить задачу по ID",
            description = "Обновляет существующую задачу по ID и возвращает обновлённые данные.",
            requestBody = @RequestBody(
                    description = "Обновлённые данные задачи",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TaskDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Задача успешно обновлена"),
                    @ApiResponse(responseCode = "400", description = "Невалидные данные"),
                    @ApiResponse(responseCode = "404", description = "Задача не найдена")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable UUID id, @Valid @RequestBody TaskDTO taskDTO) {
        TaskDTO updatedTask = taskService.updateTask(id, taskDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTask); // 200 OK
    }

    @Operation(
            summary = "Удалить задачу по ID",
            description = "Удаляет задачу по ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Задача успешно удалена"),
                    @ApiResponse(responseCode = "404", description = "Задача не найдена")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
