FROM openjdk:17-jdk-alpine
WORKDIR /pastebin/authentication-service
COPY target/pastebin-authentication-service.jar pastebin-authentication-service.jar

RUN pwd
RUN ls

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "./pastebin-authentication-service.jar"]
