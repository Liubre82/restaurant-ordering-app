FROM maven:3.9.6-eclipse-temurin-17 as build
WORKDIR /Restaurant-Ordering-App-CI-Pipeline/restaurant-ordering-app
# Copy the JAR file from the local file system into the container
COPY . .
RUN mvn clean install -f restaurant-ordering-app/pom.xml

FROM eclipse-temurin:17.0.6_10-jdk
WORKDIR /Restaurant-Ordering-App-CI-Pipeline/restaurant-ordering-app
COPY --from=build /Restaurant-Ordering-App-CI-Pipeline/restaurant-ordering-app/target/restaurant-ordering-app.jar /Restaurant-Ordering-App-CI-Pipeline/restaurant-ordering-app/
EXPOSE 8080
CMD ["java", "-jar", "restaurant-ordering-app.jar"]
