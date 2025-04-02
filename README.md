# Data Aggregator with Snusbase Integration

A web application that allows users to query database search engines and view tailored results from data leaks using Snusbase API.

## Project Structure

The project consists of two main parts:

1. **Backend (Spring Boot)**
   - RESTful API for handling authentication and search operations
   - Integration with Snusbase API for data leak searches
   - H2 in-memory database for storing user data and search history

2. **Frontend (React)**
   - Modern UI for performing searches and viewing results
   - Authentication system (login/register)
   - Dashboard for search history and results visualization

## Setup Instructions

### Prerequisites

- Java 17 or higher
- Node.js and npm
- Snusbase API key

### Backend Setup

1. Navigate to the backend directory:
   ```
   cd backend
   ```

2. Create the application.properties file from the template:
   ```
   cp src/main/resources/application.properties.template src/main/resources/application.properties
   ```

3. Edit `src/main/resources/application.properties` to set your Snusbase API key and JWT secret:
   ```
   snusbase.api.key=YOUR_SNUSBASE_API_KEY
   jwt.secret=YOUR_SECURE_JWT_SECRET_KEY
   ```
   **Note:** Never commit the application.properties file with real secrets to Git!

4. Build and run the Spring Boot application:
   ```
   ./mvnw spring-boot:run
   ```

### Frontend Setup

1. Navigate to the frontend directory:
   ```
   cd frontend
   ```

2. Install dependencies:
   ```
   npm install
   ```

3. Start the development server:
   ```
   npm start
   ```

4. Open http://localhost:3000 in your browser

## Features

- User registration and authentication
- Search data leaks using various search types:
  - Email
  - Username
  - Password hash
  - Domain
  - IP address
- View detailed search results
- Search history tracking
- Secure API communication with JWT authentication

## Security Considerations

- API keys are stored server-side only
- Passwords are hashed using BCrypt
- JWT tokens are used for authentication
- CORS is properly configured
- Sensitive configuration is excluded from version control

## License

This project is licensed under the MIT License - see the LICENSE file for details.
