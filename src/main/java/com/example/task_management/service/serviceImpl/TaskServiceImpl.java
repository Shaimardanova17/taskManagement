package com.example.task_management.service.serviceImpl;

import com.example.task_management.dto.TaskDTO;
import com.example.task_management.dto.mapping.TaskMapper;
import com.example.task_management.entity.Task;
import com.example.task_management.exception.ResourceNotFoundException;
import com.example.task_management.repository.TaskRepository;
import com.example.task_management.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    /**
     * Поиск задачи по ID.
     * Если задача не найдена, выбрасывается ResourceNotFoundException с HTTP 404.
     *
     * @param id UUID задачи
     * @return TaskDTO найденной задачи
     */
    @Override
    public TaskDTO findTaskById(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        return taskMapper.toDTO(task);
    }

    /**
     * Создание новой задачи.
     * Входные данные валидируются аннотациями в DTO (@Valid).
     * Если данные некорректны, Spring автоматически вернёт 400 Bad Request.
     *
     * @param taskDTO DTO новой задачи
     * @return TaskDTO созданной задачи с заполненным id и датами
     */
    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        task.setCreatedAt(LocalDateTime.now());
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDTO(savedTask);
    }

    /**
     * Обновление задачи по ID.
     * Можно обновить поля title, description и status.
     * Если задача не найдена — выбрасывается ResourceNotFoundException (404).
     * Если входные данные некорректны — Spring автоматически вернёт 400 Bad Request.
     *
     * @param id UUID задачи для обновления
     * @param taskDTO DTO с новыми значениями полей (можно частично)
     * @return TaskDTO обновлённой задачи
     */
    @Override
    public TaskDTO updateTask(UUID id, TaskDTO taskDTO) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));

        // Обновляем только непустые поля
        if (taskDTO.getTitle() != null) {
            existingTask.setTitle(taskDTO.getTitle());
        }
        if (taskDTO.getDescription() != null) {
            existingTask.setDescription(taskDTO.getDescription());
        }
        if (taskDTO.getStatus() != null) {
            existingTask.setStatus(taskDTO.getStatus());
        }

        // Устанавливаем время последнего обновления
        existingTask.setUpdatedAt(LocalDateTime.now());

        // Сохраняем изменения в базе
        Task savedTask = taskRepository.save(existingTask);

        return taskMapper.toDTO(savedTask);
    }

    /**
     * Удаление задачи по ID.
     * Если задача не найдена — выбрасывается ResourceNotFoundException (404).
     *
     * @param id UUID задачи для удаления
     */
    @Override
    public void deleteTask(UUID id) {
        // Проверяем наличие задачи перед удалением
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        taskRepository.delete(task);
    }
}
