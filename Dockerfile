FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY . .

# Fix permission inside docker context
RUN chmod +x gradlew

RUN ./gradlew clean build -x test

FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
