FROM eclipse-temurin:17-jdk
VOLUME /tmp
ARG JAR_FILE=./build/libs/jenkins-test-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
