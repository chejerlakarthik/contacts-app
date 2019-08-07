# Use Java8 as the base docker image
FROM java:8

# Copy the packaged Jar file
ADD target/contacts-app-1.0.0-SNAPSHOT.jar /app.jar

# Expose port 80 - more of an instruction to AWS EBS
EXPOSE 80

# Command to run inside the container to start the app
CMD ["java", "-jar", "app.jar"]