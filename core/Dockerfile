FROM openjdk:18-jdk-slim
MAINTAINER Gregory Risch <g6@exostaz.com>

RUN apt-get update && apt-get install maven -y
COPY . /build
RUN  mvn -f /build/pom.xml clean package
RUN mv /build/target/*.jar /build/target/yaba.jar

ENTRYPOINT ["java","-jar","/build/target/yaba.jar"]