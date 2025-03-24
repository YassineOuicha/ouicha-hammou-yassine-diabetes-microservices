FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/patient-service.jar patient-service.jar
ENTRYPOINT ["java","-jar","/patient-service.jar"]
