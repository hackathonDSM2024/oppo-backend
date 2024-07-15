FROM eclipse-temurin:17-jre-focal

EXPOSE 8080

ADD build/libs/demo-0.0.1-SNAPSHOT.war demo-0.0.1-SNAPSHOT.war

ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.war"]