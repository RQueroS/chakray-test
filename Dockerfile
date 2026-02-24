FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=build /app/target/test-0.0.1-SNAPSHOT.jar app.jar

ARG DEFAULT_PORT=8080
ENV PORT=${DEFAULT_PORT}
EXPOSE ${PORT}

ENTRYPOINT ["java", "-jar", "app.jar"]