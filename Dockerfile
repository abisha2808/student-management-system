# Use official Java image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Build the jar file
RUN ./mvnw clean package -DskipTests

# Expose port (Cloud Run uses 8080)
EXPOSE 8080

# Run the jar
CMD ["java", "-jar", "target/student-management-system-0.0.1-SNAPSHOT.jar"]
