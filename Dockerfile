FROM node:20 AS node-deps

WORKDIR /app

COPY package.json ./

# Use public npm registry for the Docker build
RUN printf "registry=https://registry.npmjs.org/\n" > /tmp/npmrc \
    && npm_config_userconfig=/tmp/npmrc npm install


FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=node-deps /app/node_modules ./node_modules
COPY JQuery-demo-v5.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]