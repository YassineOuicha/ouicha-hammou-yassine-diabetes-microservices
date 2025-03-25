FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/gateway-service.jar gateway-service.jar
ENTRYPOINT ["java","-jar","/gateway-service.jar"]
