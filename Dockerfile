FROM openjdk:17-jdk-alpine
# Используем легковесный образ Alpine Linux с установленным OpenJDK 17

COPY target/task_management-0.0.1-SNAPSHOT.jar app-1.0.0.jar
# Копируем собранный JAR-файл из папки target в контейнер и переименовываем его

ENTRYPOINT ["java", "-jar", "app-1.0.0.jar"]
# Устанавливаем команду запуска: выполнить JAR-файл как приложение Spring Boot
