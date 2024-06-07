FROM openjdk:17
ARG JAR_FILE=target/tingeso2-msreports.jar
COPY ${JAR_FILE} tingeso2-msreports.jar
ENTRYPOINT ["java","-jar","/tingeso2-msreports.jar"]