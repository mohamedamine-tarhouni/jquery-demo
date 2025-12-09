# Use a valid OpenJDK 17 slim image
FROM openjdk:17.0.8-jdk-slim-bullseye

# Set working directory
WORKDIR /app

# Copy the built jar and rename it to app.jar
COPY target/demoJQuery-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java","-jar","/app/app.jar"]
