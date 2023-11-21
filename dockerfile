FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
COPY target/heroes-0.0.1-SNAPSHOT.jar heroes.jar
EXPOSE 8080
CMD ["java", "-jar", "heroes.jar"]