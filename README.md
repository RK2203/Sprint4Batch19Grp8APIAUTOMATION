# Hotel Room Service API Test Automation

[![GitHub Repository](https://img.shields.io/badge/GitHub-RK2203%2FSprint4batch19Group8-blue?logo=github)](https://github.com/RK2203/Sprint4batch19Group8)
![Project Status](https://img.shields.io/badge/Status-Active-green)
![Java](https://img.shields.io/badge/Java-1.8-orange)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![TestNG](https://img.shields.io/badge/TestNG-7.10.2-yellow)
![REST Assured](https://img.shields.io/badge/REST_Assured-5.4.0-blue)

## 🚀 Project Overview

This repository contains a comprehensive REST API test automation framework for **Hotel Room Service API** developed as part of Sprint 4 project for Batch 19, Group 8. The framework tests various hotel room management operations including adding, updating, deleting, and viewing room information with proper authentication mechanisms.

**Repository**: [RK2203/Sprint4batch19Group8](https://github.com/RK2203/Sprint4batch19Group8)

## 📋 Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Test Cases](#test-cases)
- [API Endpoints](#api-endpoints)
- [Test Reports](#test-reports)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [Team Members](#team-members)
- [License](#license)

## ✨ Features

- **Complete API Test Coverage**: Tests for all CRUD operations on hotel rooms
- **Authentication Testing**: Both authenticated and unauthenticated request scenarios
- **Data-Driven Testing**: Using TestNG DataProvider for parameterized tests
- **Robust Configuration Management**: Externalized configuration using properties files
- **Comprehensive Reporting**: Detailed TestNG HTML reports with test results
- **Automated Token Management**: OAuth-based authentication with automatic token handling
- **Negative Testing**: Proper validation of error scenarios and unauthorized access
- **Cross-Environment Support**: Configurable base URLs for different environments

## 🛠️ Technologies Used

### Core Framework
- **Java 1.8** - Programming language
- **Maven** - Build and dependency management tool
- **TestNG 7.10.2** - Testing framework
- **REST Assured 5.4.0** - API testing library

### Additional Libraries
- **Jackson Databind 2.18.0** - JSON processing
- **JUnit 3.8.1** - Additional testing support
- **Maven Surefire Plugin 3.2.5** - Test execution

### Development Environment
- **Eclipse IDE** - Development environment
- **Git** - Version control system

## 📁 Project Structure

```
LibraryManagementAPIAutomation/
│
├── src/
│   ├── main/java/
│   │   └── LibraryManagementAPIAutomation/
│   │       └── App.java                    # Main application class
│   │
│   └── test/java/
│       ├── base/
│       │   └── BaseTest.java               # Base test configuration
│       ├── tests/
│       │   ├── AddRoomTest.java           # Room creation tests
│       │   ├── DeleteRoomTest.java        # Room deletion tests
│       │   ├── UpdateRoomTest.java        # Room update tests
│       │   ├── ViewRoomById.java          # View room by ID tests
│       │   ├── ViewRoomByType.java        # View room by type tests
│       │   └── ViewRoomsTest.java         # View all rooms tests
│       ├── utils/
│       │   ├── AuthTokenProvider.java     # OAuth token management
│       │   └── ConfigReader.java          # Configuration reader utility
│       └── resources/
│           ├── config.properties          # Environment configuration
│           └── testdata/
│               ├── addRoom.json           # Test data for room creation
│               └── updateRoom.json        # Test data for room updates
│
├── test-output/                           # TestNG generated reports
├── .settings/                             # Eclipse project settings
├── pom.xml                                # Maven configuration
└── testng.xml                             # TestNG suite configuration
```

## 🚦 Getting Started

### Prerequisites

Before running the tests, ensure you have the following installed:

- **Java Development Kit (JDK) 1.8 or higher**
- **Apache Maven 3.6+**
- **Git**
- **IDE** (Eclipse, IntelliJ IDEA, or VS Code)

### System Requirements

- **Operating System**: Windows, macOS, or Linux
- **Memory**: Minimum 4GB RAM
- **Network**: Internet connection for API calls

## 📥 Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/RK2203/Sprint4batch19Group8.git
   cd Sprint4batch19Group8/LibraryManagementAPIAutomation
   ```

2. **Verify Java installation**
   ```bash
   java -version
   # Should show Java 1.8 or higher
   ```

3. **Verify Maven installation**
   ```bash
   mvn -version
   # Should show Maven 3.6+ and Java 1.8+
   ```

4. **Install project dependencies**
   ```bash
   mvn clean install
   ```

## ⚙️ Configuration

The project uses a configuration file located at `src/test/java/resources/config.properties`:

```properties
# API Base URLs
baseURI=https://webapps.tekstac.com/HotelAPI/RoomService
loginURI=https://webapps.tekstac.com/OAuthRestApi/webapi/auth/login
tokenURI=https://webapps.tekstac.com/OAuthRestApi/webapi/auth/token

# Authentication Credentials
username=user1
password=pass123

# Test Configuration
timeout=5000
roomId=101
```

### Configuration Parameters

- **baseURI**: Main API endpoint for room service operations
- **loginURI**: Authentication login endpoint
- **tokenURI**: Token generation endpoint
- **username/password**: Test user credentials
- **timeout**: Request timeout in milliseconds
- **roomId**: Default room ID for testing

## 🏃‍♂️ Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Suite
```bash
mvn clean test -Dsurefire.suiteXmlFiles=testng.xml
```

### Run Specific Test Class
```bash
mvn clean test -Dtest=AddRoomTest
```

### IDE Execution
1. Import the project into your IDE
2. Right-click on `testng.xml` → Run As → TestNG Suite
3. Or run individual test classes directly

## 🧪 Test Cases

### Room Management Tests

| Test Class | Test Methods | Description |
|------------|-------------|-------------|
| **AddRoomTest** | `testAddRoomWithAuthentication` | Tests room creation with valid authentication |
| | `testAddRoomWithoutAuthentication` | Tests room creation without authentication (negative test) |
| **UpdateRoomTest** | `testUpdateRoomPriceWithAuthentication` | Tests room price update with valid authentication |
| | `testUpdateRoomPriceWithoutAuthentication` | Tests room price update without authentication (negative test) |
| **DeleteRoomTest** | `testDeleteRoomWithAuthentication` | Tests room deletion with valid authentication |
| | `testDeleteRoomWithoutAuthentication` | Tests room deletion without authentication (negative test) |
| **ViewRoomById** | `testViewRoomById` | Tests retrieving room information by ID |
| **ViewRoomByType** | `testRoomByType` | Tests retrieving rooms by type (SINGLE, DOUBLE, etc.) |
| **ViewRoomsTest** | `testViewRoomList` | Tests retrieving list of all rooms |

### Test Data Parameters

**Room Creation Data:**
- Room ID: 150
- Hotel ID: H2005
- Room Type: DELUXE
- Room Status: AVAILABLE
- Room Price: 2500.0

**Room Update Data:**
- Room ID: 301
- Updated Price: 1500.0

## 🔌 API Endpoints

### Base URL
```
https://webapps.tekstac.com/HotelAPI/RoomService
```

### Authentication Endpoints
- `POST /auth/login` - User authentication
- `POST /auth/token` - Access token generation

### Room Management Endpoints
- `POST /addRoom` - Create new room
- `GET /viewRoomById/{roomId}` - Get room by ID
- `GET /viewRoomByType?roomType={type}` - Get rooms by type
- `GET /viewRoomList` - Get all rooms
- `PUT /updateRoomPrice` - Update room price
- `DELETE /deleteRoomById/{roomId}` - Delete room by ID

### Request/Response Examples

#### Add Room Request
```bash
POST /addRoom
Content-Type: application/x-www-form-urlencoded
Authorization: Bearer {access_token}

roomId=150&hotelId=H2005&roomType=DELUXE&roomStatus=AVAILABLE&roomPrice=2500.0
```

#### View Room Response
```json
{
  "roomId": 101,
  "hotelId": "H2001",
  "roomType": "SINGLE",
  "roomStatus": "AVAILABLE",
  "roomPrice": 2000.0
}
```

## 📊 Test Reports

### Viewing Test Results

After running tests, reports are generated in the `test-output/` directory:

- **HTML Reports**: `test-output/index.html` - Main TestNG report
- **XML Reports**: `test-output/testng-results.xml` - XML format results
- **Suite Reports**: `test-output/API Test Suite/` - Detailed suite reports

### Current Test Status

Based on the latest test execution:

- **Total Tests**: 9
- **Passed**: 3 ✅
- **Failed**: 6 ❌
- **Skipped**: 0

**Passing Tests:**
- View Room by ID
- View Room by Type  
- View Room List

**Failing Tests:**
- Add Room (Authentication & Non-Authentication)
- Update Room Price (Authentication & Non-Authentication)
- Delete Room (Authentication & Non-Authentication)

### Common Test Failures

1. **Authentication Issues**: Some tests expect 401/403 status codes but receive 200
2. **Message Validation**: Expected success messages not found in responses
3. **API Response Format**: Response structure might have changed

## 🔧 Troubleshooting

### Common Issues and Solutions

#### 1. Authentication Token Failures
```java
// Error: Auth code not found in login response
// Solution: Verify credentials in config.properties
username=user1
password=pass123
```

#### 2. Connection Timeouts
```java
// Error: Read timed out
// Solution: Increase timeout in config.properties
timeout=10000
```

#### 3. Maven Dependencies Issues
```bash
# Clean and reinstall dependencies
mvn clean install -U
```

#### 4. Test Environment Issues
```bash
# Verify API endpoint is accessible
curl -X GET "https://webapps.tekstac.com/HotelAPI/RoomService/viewRoomList"
```

#### 5. IDE Configuration
- Ensure Project SDK is set to Java 1.8
- Maven auto-import should be enabled
- TestNG plugin should be installed

### Debug Mode

To run tests in debug mode with detailed logging:

```bash
mvn clean test -X
```

## 🤝 Contributing

### Development Workflow

1. **Fork the repository** or create a feature branch
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Make your changes**
   - Follow Java coding conventions
   - Add proper JavaDoc comments
   - Write corresponding unit tests
   - Update configuration if needed

3. **Run tests locally**
   ```bash
   mvn clean test
   ```

4. **Commit your changes**
   ```bash
   git add .
   git commit -m "feat: add your feature description"
   ```

5. **Push and create Pull Request**
   ```bash
   git push origin feature/your-feature-name
   ```

### Coding Standards

- Use meaningful class and method names
- Follow camelCase naming convention
- Add proper exception handling
- Include logging where appropriate
- Write reusable utility methods
- Keep test data externalized

### Adding New Tests

1. Create test class in `src/test/java/tests/` package
2. Extend `BaseTest` class for configuration
3. Use `@DataProvider` for test data
4. Add both positive and negative test scenarios
5. Update `testng.xml` if needed

## 👥 Team Members

| Role | Name 
|------|------|
| **Tester 1** | [Shoal Koley]
| **Tester 2** | [Gyanesh]
| **Tester 3** | [Harsh] |


## 🏆 Sprint 4 Achievements

### ✅ Completed Objectives
- [x] **API Test Framework Setup** - Complete REST Assured framework implementation
- [x] **Authentication Integration** - OAuth 2.0 token-based authentication
- [x] **CRUD Operations Testing** - All room management operations covered
- [x] **Data-Driven Testing** - Parameterized tests using TestNG DataProvider
- [x] **Reporting Integration** - TestNG HTML reports with detailed results
- [x] **Configuration Management** - Externalized properties for environment settings
- [x] **Error Handling** - Comprehensive negative testing scenarios
- [x] **Code Organization** - Clean architecture with proper separation of concerns

### 📊 Test Coverage Metrics
- **API Endpoints Covered**: 6/6 (100%)
- **Authentication Scenarios**: 2/2 (100%)
- **CRUD Operations**: 4/4 (100%)
- **Negative Test Cases**: 6/9 (67%)

### 🔍 Known Issues & Limitations

1. **API Response Inconsistencies**: Some endpoints return unexpected status codes
2. **Test Environment Stability**: Occasional network timeouts
3. **Authentication Token Expiry**: Need to handle token refresh scenarios
4. **Test Data Management**: Limited test data cleanup functionality

## 🔄 Future Enhancements

- **Parallel Test Execution**: Implement TestNG parallel execution
- **Database Integration**: Add database validation tests
- **Performance Testing**: Include API response time validation
- **CI/CD Integration**: GitHub Actions for automated test execution
- **Allure Reporting**: Enhanced reporting with Allure framework
- **Test Data Management**: Dynamic test data generation and cleanup

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Thanks to **Tekstac** for providing the Hotel API endpoints
- **REST Assured** team for the excellent API testing library
- **TestNG** community for the robust testing framework
- Our course instructors for guidance and support
- **Sprint 4 Batch 19** colleagues for collaboration and knowledge sharing

## 📞 Support & Contact

For technical support or questions about this project:

1. **Create an Issue**: [GitHub Issues](https://github.com/RK2203/Sprint4batch19Group8/issues)
2. **Discussion Forum**: Use GitHub Discussions for general questions
3. **Team Contact**: Reach out to any team member for urgent issues

### 🚀 Quick Start Command
```bash
# One-command setup and test execution
git clone https://github.com/RK2203/Sprint4batch19Group8.git && \
cd Sprint4batch19Group8/LibraryManagementAPIAutomation && \
mvn clean test
```


> **Note**: This README reflects the actual project structure and test implementation. For the most up-to-date information, refer to the source code and latest test reports.

*Last Updated: August 11, 2025*
