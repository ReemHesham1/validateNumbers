FROM adoptopenjdk/openjdk11:ubi
COPY ./target/validateNumber-app.jar validateNumber-app.jar
ENTRYPOINT ["java","-jar","validateNumber-app.jar"]