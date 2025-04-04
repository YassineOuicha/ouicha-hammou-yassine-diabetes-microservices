# Use OpenJDK 17 with Maven as the base image
FROM maven:3.8.5-openjdk-17

# Set the working directory inside the container to /app
WORKDIR /app

# Copy the Maven configuration file (pom.xml) to the container
COPY pom.xml .

# Copy the source code directory (src) to the container
COPY src src

# Build the application using Maven
RUN mvn clean package -DskipTests

# Expose port 8080 for the application to listen on
EXPOSE 8080

# Run the application by executing the JAR file with the java command
CMD ["java", "-jar", "target/gateway.jar"]
