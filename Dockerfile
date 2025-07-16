# Stage de compilation
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage de production
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/anime-watchlist-api-1.0.0.jar app.jar
EXPOSE 26550
ENTRYPOINT ["java", "-jar", "app.jar"] 