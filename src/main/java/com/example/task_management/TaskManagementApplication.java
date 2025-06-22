package com.example.task_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskManagementApplication {

    public static void main(String[] args) {
        // Выводим приветственное сообщение в консоль (опционально)
        System.out.println("Hello World");

        // Запускаем Spring Boot приложение, передавая аргументы командной строки
        SpringApplication.run(TaskManagementApplication.class, args);
    }
}
