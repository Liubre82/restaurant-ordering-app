FROM maven:3.9.6-eclipse-temurin-17 as build
WORKDIR /app
# Copy the JAR file from the local file system into the container
COPY . .
RUN mvn clean install -f restaurant-ordering-app/pom.xml

FROM eclipse-temurin:17.0.6_10-jdk
WORKDIR /app
COPY --from=build /app/restaurant-ordering-app/target/restaurant-ordering-app.jar /apps/
EXPOSE 8080
CMD ["java", "-jar", "restaurant-ordering-app.jar"]
