# Use Eclipse Temurin JDK 17 (actively maintained)
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy built jar under a consistent name
COPY demoJQuery-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
