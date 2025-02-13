# --------------- BUILD STAGE ---------------
FROM openjdk:17-jdk-slim AS build

WORKDIR /app

COPY build.gradle /app/
COPY gradle /app/gradle
COPY src /app/src

COPY gradlew /app/

RUN chmod +x ./gradlew

RUN ./gradlew build --parallel -no-daemon

# -------------------------------------------

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/app.jar
COPY src /app/src

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]