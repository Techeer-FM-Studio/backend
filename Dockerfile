FROM gradle:7.6-jdk17

WORKDIR /app
ENV CONFIG_SERVER_IP localhost
ENV EUREKA_SERVER_IP localhost

COPY ./build/libs/fm-studio-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "fm-studio-0.0.1-SNAPSHOT.jar"]