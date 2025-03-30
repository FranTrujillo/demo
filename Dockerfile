FROM openjdk:21-jdk-slim
WORKDIR /app
ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
COPY src/main/resources/db/changelog /app/db/changelog
ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["java", "-jar", "app.jar", "--spring.liquibase.change-log=db/changelog/db.changelog-master.xml"]