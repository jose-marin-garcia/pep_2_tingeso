FROM openjdk:17
ARG JAR_FILE=target/tingeso2-msvehicles.jar
COPY ${JAR_FILE} tingeso2-msvehicles.jar
ENTRYPOINT ["java","-jar","/tingeso2-msvehicles.jar"]