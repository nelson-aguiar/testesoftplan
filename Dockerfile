FROM openjdk:8-jre-slim
WORKDIR /tmp/deployment
EXPOSE 8080
COPY target/*.jar /tmp/deployment/
CMD java -jar /tmp/deployment/teste-softplan-0.0.1-SNAPSHOT.jar 