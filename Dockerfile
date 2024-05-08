# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from your target directory into the Docker image
COPY ./target/bank-0.0.1-SNAPSHOT.jar /app/bank_demo_spring_boot.jar

# Make port 8082 available to the world outside this container
EXPOSE 8082

# Run the jar file
CMD ["java", "-jar", "/app/bank_demo_spring_demo.jar"]
