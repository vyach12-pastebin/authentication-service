FROM openjdk:17-jdk-alpine
WORKDIR /pastbin/authentication-service
COPY target/pastbin-authentication-service.jar pastbin-authentication-service.jar

RUN pwd
RUN ls

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "./pastbin-authentication-service.jar"]
