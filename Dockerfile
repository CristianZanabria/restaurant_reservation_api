FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/restaurant_reservation_api-0.0.1.jar
COPY ${JAR_FILE} restaurant_reservation_api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "restaurant_reservation_api.jar"]