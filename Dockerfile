FROM eclipse-temurin:21-jdk

WORKDIR /app

# Install Node.js and npm
RUN apt-get update \
    && apt-get install -y nodejs npm \
    && rm -rf /var/lib/apt/lists/*

# Copy package.json
COPY package.json package-lock.json ./

RUN npm config set registry https://registry.npmjs.org/

# Install frontend dependencies
RUN npm install

# Copy Spring Boot JAR
COPY JQuery-demo-v5.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]