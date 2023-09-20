FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} container-platform-common-api.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/container-platform-common-api.jar"]