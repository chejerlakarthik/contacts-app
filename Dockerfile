FROM java:8
ADD target/contacts-app-1.0.0-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]