package com.example.task_management.dto.mapping;

import com.example.task_management.dto.TaskDTO;
import com.example.task_management.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") // Используем Spring для автоматического внедрения маппера
public interface TaskMapper {

   /**
    * Преобразует сущность Task в DTO TaskDTO.
    * DTO используется для передачи данных в контроллеры и клиентам.
    */
   TaskDTO toDTO(Task task);

   /**
    * Преобразует DTO TaskDTO в сущность Task.
    * Поля createdAt и updatedAt игнорируются,
    * чтобы эти временные метки устанавливались только в сервисном слое.
    */
   @Mapping(target = "createdAt", ignore = true)
   @Mapping(target = "updatedAt", ignore = true)
   Task toEntity(TaskDTO taskDTO);
}
