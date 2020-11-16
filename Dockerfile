# Build Stage for Spring boot application image
FROM openjdk:11-jdk-slim

ENV APP_TARGET /target
ENV APP tec-sales-api.war

RUN mkdir -p /environment
COPY ${APP_TARGET}/${APP} /environment

CMD ["java","-Djava.awt.headless=true","-Dspring.profiles.active=dev","-jar","/environment/tec-sales-api.war"]
