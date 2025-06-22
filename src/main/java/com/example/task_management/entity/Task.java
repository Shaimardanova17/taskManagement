package com.example.task_management.entity;

import com.example.task_management.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks") // Отображение сущности на таблицу "tasks" в базе данных
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    /**
     * Уникальный идентификатор задачи.
     * Генерируется автоматически (UUID).
     */
    @Id
    @GeneratedValue
    private UUID id;

    /**
     * Заголовок задачи.
     * Не может быть пустым (nullable = false), максимум 100 символов.
     */
    @Column(nullable = false, length = 100)
    private String title;

    /**
     * Описание задачи.
     * Хранится в виде текста произвольной длины.
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Статус задачи.
     * Хранится в базе в виде строки (имя enum-константы).
     * Обязательное поле (nullable = false).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    /**
     * Дата и время создания задачи.
     * Устанавливается автоматически при сохранении, не может быть изменено вручную.
     */
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    /**
     * Дата и время последнего обновления задачи.
     * Автоматически обновляется при каждом изменении сущности.
     */
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
