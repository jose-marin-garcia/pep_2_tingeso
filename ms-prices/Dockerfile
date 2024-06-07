FROM openjdk:17
ARG JAR_FILE=target/tingeso2-msprices.jar
COPY ${JAR_FILE} tingeso2-msprices.jar
ENTRYPOINT ["java","-jar","/tingeso2-msprices.jar"]