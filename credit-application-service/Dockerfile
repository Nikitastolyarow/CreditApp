# Базовый образ с Java
FROM openjdk:17-jdk-slim

# Рабочая директория
WORKDIR /app

# Копируем файл сборки Maven
COPY pom.xml .
COPY src ./src

# Копируем Maven Wrapper (если используется)
COPY mvnw .
COPY .mvn .mvn

# Собираем приложение
RUN ./mvnw clean package -DskipTests

# Указываем команду для запуска приложения
CMD ["java", "-jar", "target/credit-application-service-0.0.1-SNAPSHOT.jar"]