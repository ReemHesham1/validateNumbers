FROM adoptopenjdk/openjdk11:ubi
COPY ./target/validate-number-app.jar validate-number-app.jar
ENTRYPOINT ["java","-jar","validate-number-app.jar"]