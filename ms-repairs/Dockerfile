FROM openjdk:17
ARG JAR_FILE=target/tingeso2-msrepairs.jar
COPY ${JAR_FILE} tingeso2-msrepairs.jar
ENTRYPOINT ["java","-jar","/tingeso2-msrepairs.jar"]