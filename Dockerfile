# Use an OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built jar (assumes Maven or Gradle build output)
COPY target/demoJQuery-0.0.1-SNAPSHOT.jar app.jar

# Expose port (must match Spring Boot server.port)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java","-jar","/app/app.jar"]
