# Use Eclipse Temurin JDK 17 (actively maintained)
FROM FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy built jar under a consistent name
COPY demoJQuery-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
