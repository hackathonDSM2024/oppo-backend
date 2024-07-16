FROM eclipse-temurin:17-jre-focal

EXPOSE 8080

ARG CHATGPT_API_KEY
ENV CHATGPT_API_KEY ${CHATGPT_API_KEY}

ADD build/libs/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]