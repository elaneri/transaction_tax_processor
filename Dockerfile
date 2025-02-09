FROM openjdk:17-slim
MAINTAINER shvm.cloud
COPY target/tax-processor-0.0.1-SNAPSHOT.jar tax-processor-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/tax-processor-0.0.1-SNAPSHOT.jar"]