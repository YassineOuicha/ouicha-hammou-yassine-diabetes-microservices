FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/risk-service.jar risk-service.jar
ENTRYPOINT ["java","-jar","/risk-service.jar"]
