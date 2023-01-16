FROM adoptopenjdk/openjdk8

ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY ./target/demoBollo-0.0.1-SNAPSHOT.jar demoBollo-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "demoBollo-0.0.1-SNAPSHOT.jar"]