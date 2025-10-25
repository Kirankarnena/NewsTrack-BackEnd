# 1️⃣ Stage 1 — Build the JAR
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the pom.xml and download dependencies (cached layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the source code and build the JAR
COPY src ./src
RUN mvn clean package -DskipTests

# 2️⃣ Stage 2 — Run the app
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copy JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
