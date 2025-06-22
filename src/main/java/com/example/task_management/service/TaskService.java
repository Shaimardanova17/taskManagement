package com.example.task_management.service;

import com.example.task_management.dto.TaskDTO;
import com.example.task_management.entity.Task;
import com.example.task_management.exception.ResourceNotFoundException;
import jakarta.validation.ValidationException;

import java.util.UUID;
/**
 * Интерфейс сервиса для управления задачами.
 * Определяет базовые операции CRUD для сущности Task.
 */
public interface TaskService {

    /**
     * Поиск задачи по её уникальному идентификатору.
     *
     * @param id UUID задачи
     * @return DTO найденной задачи
     * @throws ResourceNotFoundException если задача с таким ID не существует
     */
    TaskDTO findTaskById(UUID id);

    /**
     * Создание новой задачи.
     *
     * @param taskDTO DTO с данными новой задачи
     * @return DTO созданной задачи с присвоенным ID и датами
     * @throws ValidationException при некорректных входных данных
     */
    TaskDTO createTask(TaskDTO taskDTO);

    /**
     * Обновление существующей задачи.
     * Можно обновить title, description или status.
     *
     * @param id UUID задачи для обновления
     * @param taskDTO DTO с новыми данными задачи (частичное обновление поддерживается)
     * @return DTO обновлённой задачи
     * @throws ResourceNotFoundException если задача с таким ID не найдена
     * @throws ValidationException при некорректных входных данных
     */
    TaskDTO updateTask(UUID id, TaskDTO taskDTO);

    /**
     * Удаление задачи по её ID.
     *
     * @param id UUID задачи для удаления
     * @throws ResourceNotFoundException если задача с таким ID не найдена
     */
    void deleteTask(UUID id);
}

