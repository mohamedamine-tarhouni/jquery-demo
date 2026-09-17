FROM eclipse-temurin:21-jdk

WORKDIR /app

# Install frontend dependencies
RUN npm install

# Copy Spring Boot JAR
COPY JQuery-demo-v5.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]