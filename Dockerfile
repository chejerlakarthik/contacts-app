# Use Java11 as the base docker image
FROM adoptopenjdk/openjdk11:ubi-minimal-jre

# Our working directory inside the container
WORKDIR /app

# Copy the packaged Jar file
COPY build/libs/contacts-app-*.jar app.jar

# Expose port 80
EXPOSE 80

# Command to run inside the container to start the app
CMD ["java", "-jar", "app.jar"]