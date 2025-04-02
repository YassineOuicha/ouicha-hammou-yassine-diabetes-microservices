# Use OpenJDK 17 slim image as the base image for a lightweight runtime environment
FROM openjdk:17-jdk-slim

# Set the working directory inside the container to /app
WORKDIR /app

# Copy the built JAR file from the target directory to the container and rename it to app.jar
COPY target/front-service-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8082 for the application to listen on
EXPOSE 8082

# Run the application by executing the JAR file using the java command
CMD ["java", "-jar", "app.jar"]
