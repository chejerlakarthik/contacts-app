# Use Java8 as the base docker image
FROM java:8

# Our working directory inside the container
WORKDIR /app

# Copy the packaged Jar file
COPY target/contacts-app-1.0.0-SNAPSHOT.jar contacts-app.jar

# Expose port 8080 - more of an instruction to AWS EBS
EXPOSE 8080

# Command to run inside the container to start the app
CMD ["java", "-jar", "contacts-app.jar"]