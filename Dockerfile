FROM eclipse-temurin:21

WORKDIR /app

COPY .. .

CMD ["java", "-jar", "target/dotaTest-0.0.1-SNAPSHOT.jar"]