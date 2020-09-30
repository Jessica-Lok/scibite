FROM openjdk:11
COPY . ~/code/scibite/
WORKDIR ~/code/scibite/
RUN ./gradlew build -x test 
CMD ["./gradlew", "run"]

