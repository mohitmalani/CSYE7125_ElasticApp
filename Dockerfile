FROM gcr.io/distroless/java17-debian11
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
EXPOSE 8080
ADD target/elastic-app-0.0.1-SNAPSHOT.jar elastic-app-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "elastic-app-0.0.1-SNAPSHOT.jar"]
