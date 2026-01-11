# Use a lightweight JDK image
FROM eclipse-temurin:17-jdk
LABEL authors="snehalsinkar"

# Set the working directory
WORKDIR /app

# Copy the jar file (built by Maven) into the container
COPY target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]