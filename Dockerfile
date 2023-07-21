FROM adoptopenjdk:11-jre-hotspot

WORKDIR /app

COPY target/Technical-test-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
