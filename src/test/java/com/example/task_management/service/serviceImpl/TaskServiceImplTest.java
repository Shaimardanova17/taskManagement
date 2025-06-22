package com.example.task_management.service.serviceImpl;

import com.example.task_management.dto.TaskDTO;
import com.example.task_management.dto.mapping.TaskMapper;
import com.example.task_management.entity.Task;
import com.example.task_management.enums.Status;
import com.example.task_management.exception.ResourceNotFoundException;
import com.example.task_management.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Подключает поддержку Mockito в JUnit 5 (автоматическая инициализация @Mock, @InjectMocks и т.п.)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository; // Мокаем репозиторий, чтобы не ходить в базу

    @Mock
    private TaskMapper taskMapper; // Мокаем маппер, чтобы контролировать преобразование сущностей в DTO

    @InjectMocks
    private TaskServiceImpl taskService; // Создаём экземпляр сервиса и внедряем в него моки выше

    @Test
    void findTaskByIdShouldReturnTask() {
        // Подготовка данных: создаём UUID и сущность Task
        UUID id = UUID.randomUUID();
        Task task = new Task(
                id,
                "новая задача",
                "написать юнит тесты",
                Status.PENDING,
                LocalDateTime.now(), // createdAt
                LocalDateTime.now()  // updatedAt
        );

        // Подготовка ожидаемого результата — DTO, соответствующий Task
        TaskDTO taskDTO = new TaskDTO(
                id,
                "новая задача",
                "написать юнит тесты",
                Status.PENDING
        );

        // Поведение мока: при вызове findById возвращается Optional с task
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        // Поведение мока: при преобразовании task в DTO возвращается заранее созданный taskDTO
        when(taskMapper.toDTO(task)).thenReturn(taskDTO);

        // Выполняем вызов сервиса
        TaskDTO result = taskService.findTaskById(id);

        // Проверяем, что результат не null
        assertNotNull(result);

        // Проверяем, что результат соответствует ожидаемому DTO
        assertEquals(taskDTO, result);
    }

    @Test
    void findTaskByIdShouldThrowExceptionWhenNotFound() {
        UUID id = UUID.randomUUID(); // ID несуществующей задачи

        // Мокаем поведение репозитория: ничего не найдено
        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        // Проверяем, что метод бросает исключение ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> taskService.findTaskById(id));
    }

}
