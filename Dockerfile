FROM openjdk:11

COPY ["./target/credit-0.0.1-SNAPSHOT.jar", "/usr/app/"]

CMD ["java", "-jar", "/usr/app/credit-0.0.1-SNAPSHOT.jar"]

EXPOSE 8082