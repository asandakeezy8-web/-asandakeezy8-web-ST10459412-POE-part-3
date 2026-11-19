# ST10459412 - POE Part 3 - QuickChat

## Project Overview
QuickChat is a Java console-based messaging application developed for the PROG5121 Programming POE at Varsity College. The application implements a complete user registration and login system with advanced messaging features including sending, storing, searching, and deleting messages.

This final part focuses on message management functionality with persistent JSON storage and is integrated with GitHub Actions for automated unit testing as per the module requirements.

## Features

### Part 1 - Registration & Login
- Username validation (must contain underscore and <=5 chars)
- Password complexity check
- User registration and login

### Part 2 - Message Basics
- Send message with auto-generated Message ID
- Validate message length (max 250 characters)
- Create message hash
- Display sent messages

### Part 3 - Final (Current)
- Store messages in storedMessages.json
- Display all sent messages
- Display the longest sent message
- Search message by Message ID
- Search all messages sent to a specific recipient
- Delete message using message hash
- Generate full message report

## Folder Structure
The folder structure in this repository follows the standard Maven project layout as required:

src/
├── main/
│   └── java/
│       └── com/
│           └── mycompany/
│               └── main/
│                   ├── Login.java
│                   ├── Main.java
│                   ├── Message.java
│                   └── QuickChat.java
└── test/
    └── java/
        └── com/
            └── mycompany/
                └── main/
                    ├── MessageTest.java
                    └── MessagePart3Test.java

Root files:
- pom.xml
- storedMessages.json
- .gitignore
- .github/workflows/maven.yml
- README.md

## Automated Unit Testing - GitHub Actions
As required in the announcement, this repository is configured with automated unit testing.

Every time code is pushed to the main branch, GitHub Actions automatically executes:

mvn test

Workflow location: .github/workflows/maven.yml

To view results:
1. Go to your GitHub repository
2. Click on the Actions tab
3. You will see the latest workflow run with a green checkmark if all tests pass

This satisfies the Automated Testing requirement for POE Part 3.

## Unit Tests Included
All unit tests are located in src/test/java/com/mycompany/main/

1. MessagePart3Test.java
   - Tests all Part 3 functionalities
   - Verifies message storage, searching, deleting, and reporting
   - This is the final test file Mr Godana checks

2. MessageTest.java
   - Tests core message functionality from Part 2

To run tests locally:
mvn test

## How to Run the Project

### Prerequisites
- JDK 17 or higher
- NetBeans IDE or IntelliJ IDEA
- Maven

### Steps
1. Clone this repository:
   git clone https://github.com/asandakeezy8-web/asandakeezy8-web-ST10459412-POE-part-3.git

2. Open the project in NetBeans as Maven Project

3. Build the project:
   Clean and Build or run mvn clean install

4. Run tests:
   mvn test or Right-click project > Test

5. Run the application:
   Run Main.java or mvn exec:java

## Technologies Used
- Java 17
- Apache Maven
- JUnit 5.10.0
- org.json for JSON handling
- GitHub Actions for CI/CD

## Student Details
- Student Number: ST10459412
- Module: PROG5121 - Programming 1A
- Lecturer: Mr Godana
- Assessment: POE Part 3 - Final Submission
- Institution: Varsity College / IIE

## Submission Links
- GitHub Repository: https://github.com/asandakeezy8-web/asandakeezy8-web-ST10459412-POE-part-3


## Declaration
This work is my own original work in accordance with the IIE plagiarism policy. All code has been tested and passes all unit tests via automated GitHub Actions.

