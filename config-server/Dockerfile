FROM openjdk:17
ARG JAR_FILE=target/tingeso2-configserver.jar
COPY ${JAR_FILE} tingeso2-configserver.jar
ENTRYPOINT ["java","-jar","/tingeso2-configserver.jar"]