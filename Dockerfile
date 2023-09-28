# Use an official OpenJDK runtime as a parent image
FROM openjdk:11

# Set the working directory to /app
WORKDIR /app

# Copy the packaged JAR file into the container at /app
COPY target/your-app.jar /app

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Define environment variables (if needed)
# ENV VARIABLE_NAME=value

# Run your application using CMD
CMD ["java", "-jar", "your-app.jar"]
