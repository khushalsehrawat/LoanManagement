# -------- Build stage --------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy only pom first for better caching
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw mvnw
RUN chmod +x mvnw
RUN ./mvnw -q -DskipTests dependency:go-offline

# Copy source and build
COPY src src
RUN ./mvnw -DskipTests clean package

# -------- Run stage --------
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built jar
COPY --from=build /app/target/*.jar app.jar

# Spring Boot will listen here
EXPOSE 8080

# Render/containers: bind to 0.0.0.0 automatically with server.port
ENTRYPOINT ["java","-jar","app.jar"]
