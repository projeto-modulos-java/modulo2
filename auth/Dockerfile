FROM maven:3.9.2-eclipse-temurin-17

RUN mkdir /project

WORKDIR /project

COPY src src
COPY pom.xml pom.xml

RUN mvn clean package

CMD java -jar target/auth.jar