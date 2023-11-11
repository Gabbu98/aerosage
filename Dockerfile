FROM openjdk:21-jdk
EXPOSE 8080
ADD target/aerosage-0.1.jar aerosage-0.1.jar
ENTRYPOINT ["java", "-jar", "/aerosage-0.1.jar"]

