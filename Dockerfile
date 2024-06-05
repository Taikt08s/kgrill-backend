FROM maven:3.8.5-openjdk-17 AS build

# Copy the Maven settings file
COPY settings.xml /root/.m2/settings.xml

COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build target/kgrill-0.0.1-SNAPSHOT.jar demo1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo1.jar"]