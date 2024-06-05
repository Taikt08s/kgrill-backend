FROM maven:3.8.5-openjdk-17 AS build

# ARGs for passing the GitHub credentials
ARG GITHUB_USERNAME
ARG GITHUB_TOKEN

# Create a settings.xml file with the ARG values
RUN mkdir -p /root/.m2 && echo '<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" \
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" \
    xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd"> \
  <servers> \
    <server> \
      <id>github</id> \
      <username>${GITHUB_USERNAME}</username> \
      <password>${GITHUB_TOKEN}</password> \
    </server> \
  </servers> \
  </settings>' > /root/.m2/settings.xml


COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build target/kgrill-0.0.1-SNAPSHOT.jar demo1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo1.jar"]