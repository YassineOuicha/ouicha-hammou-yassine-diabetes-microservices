# Use OpenJDK 17 slim image as the base image for a lightweight runtime environment
FROM maven:3.8.5-openjdk-17

# Set the working directory inside the container to /app
WORKDIR /app

COPY pom.xml .
COPY src src
RUN mvn clean package

# Expose port 8084 for the application to listen on
EXPOSE 8084

# Run the application by executing the JAR file using the java command
CMD ["java", "-jar", "target/risk.jar"]
