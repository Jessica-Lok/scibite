FROM adoptopenjdk:11-jre-hotspot
COPY build/libs/scibite-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "scibite-0.0.1-SNAPSHOT.jar"]

