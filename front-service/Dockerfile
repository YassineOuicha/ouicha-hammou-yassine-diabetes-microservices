FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/front-service.jar front-service.jar
ENTRYPOINT ["java","-jar","/front-service.jar"]
