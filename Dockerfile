# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# The application's jar file
ARG JAR_FILE=target/eshopping-app-1.0-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} eshopping-app.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eshopping-app.jar"]