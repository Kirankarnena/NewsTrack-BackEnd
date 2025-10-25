# 1️⃣ Use lightweight JDK
FROM openjdk:21-jdk-slim

# 2️⃣ Set working directory
WORKDIR /app

# 3️⃣ Copy jar from target folder
COPY target/*.jar app.jar

# 4️⃣ Expose Spring Boot port
EXPOSE 8080

# 5️⃣ Start the app
ENTRYPOINT ["java", "-jar", "app.jar"]
