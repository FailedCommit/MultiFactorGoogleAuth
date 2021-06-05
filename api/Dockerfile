FROM maven:3.6.3-jdk-11-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY . /workspace
RUN mvn -f pom.xml clean package
RUN cp /workspace/target/*.jar /workspace/api.jar
EXPOSE 8080
