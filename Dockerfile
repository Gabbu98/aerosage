FROM maven:3.9.5-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/aerosage-0.1.jar aerosage.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/aerosage-0.1.jar"]

