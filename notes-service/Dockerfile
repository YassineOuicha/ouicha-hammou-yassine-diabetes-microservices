FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/notes-service.jar app.jar
EXPOSE 8083
CMD ["java", "-jar", "app.jar"]
