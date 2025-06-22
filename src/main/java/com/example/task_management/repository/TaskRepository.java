package com.example.task_management.repository;

import com.example.task_management.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Репозиторий для работы с сущностью Task.
 * Наследует JpaRepository, что предоставляет стандартные CRUD операции
 * и возможности для работы с базой данных.
 *
 * @see JpaRepository
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
}
