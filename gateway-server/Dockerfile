FROM openjdk:17
ARG JAR_FILE=target/tingeso2-gatewayserver.jar
COPY ${JAR_FILE} tingeso2-gatewayserver.jar
ENTRYPOINT ["java","-jar","/tingeso2-gatewayserver.jar"]