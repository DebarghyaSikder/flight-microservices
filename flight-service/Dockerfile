FROM eclipse-temurin:19-jdk-alpine

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar"]
