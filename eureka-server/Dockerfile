FROM openjdk:17
ARG JAR_FILE=target/tingeso2-eurekaserver.jar
COPY ${JAR_FILE} tingeso2-eurekaserver.jar
ENTRYPOINT ["java","-jar","/tingeso2-eurekaserver.jar"]