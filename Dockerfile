FROM openjdk:17-jdk-alpine

# Копируем JAR-файл в образ
COPY target/SeverstalNoteTask-0.0.1-SNAPSHOT.jar severstal.jar

# Устанавливаем рабочую директорию
WORKDIR /

# Запускаем JAR-файл
CMD ["java", "-jar", "severstal.jar"]