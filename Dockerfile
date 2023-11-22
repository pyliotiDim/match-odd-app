FROM openjdk:17
MAINTAINER dpylioti
COPY target/match.odd.app-0.0.1-SNAPSHOT.jar match.odd.app-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/match.odd.app-0.0.1-SNAPSHOT.jar"]
