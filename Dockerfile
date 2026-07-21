FROM eclipse-temurin:21-jre
LABEL maintainer="paulo.lira.2002@gmail.com"
WORKDIR /locadora-api
COPY target/locadora-veiculos-0.0.1-SNAPSHOT.jar locadora.jar
ENTRYPOINT ["java","-jar","locadora.jar"]
EXPOSE 8080