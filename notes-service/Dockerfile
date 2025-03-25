FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/notes-service.jar notes-service.jar
ENTRYPOINT ["java","-jar","/notes-service.jar"]
