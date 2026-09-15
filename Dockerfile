FROM eclipse-temurin:21-jdk

WORKDIR /app

RUN apt-get update \
    && apt-get install -y nodejs npm \
    && rm -rf /var/lib/apt/lists/*

COPY package.json package-lock.json ./

RUN npm ci

COPY JQuery-demo-v5.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]