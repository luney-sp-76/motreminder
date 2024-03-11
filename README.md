## MOT Booking Timely Reminder Website 

# Introduction
The MOT Booking Timely Reminder Website aims to address the significant backlog of MOT appointments resulting from the COVID-19 pandemic. By providing a user-friendly platform, the project seeks to mitigate the risks and inconveniences experienced by drivers, including increased insurance costs and the potential for driving without valid MOT certification.

# Requirement Analysis
Purpose
The closure of MOT centres during the COVID-19 pandemic has led to a persistent backlog, complicating the process for drivers to keep their vehicles insured and MOT-certified. This project aims to offer a reliable solution to manage MOT reminders more efficiently, reducing the risk of uninsured and uncertified driving.

# Target Audience
Drivers affected by the MOT backlog, particularly those struggling to book their MOTs in a timely manner due to delayed official reminders, leading to potential legal and financial repercussions.

# Functional Requirements
Overview
Users can input their car registration number to receive car details, including tax and MOT due dates.

# Detailed Functions
Users can create and manage accounts.
Option to pay for tax due reminders.
Functionality to cancel, set, and update email reminders.
Account management features, including email and password updates.
Immediate MOT due date alerts if within 3 months, with optional paid reminder service.
Non-Functional Requirements
No storage of car registration numbers, only MOT due dates.
Secure, non-relational database for user information.
Reminder logic based on backend calculations, including leap year considerations and custom reminder dates.
## System Design
# Architecture Design
The website will utilize the Model-View-Controller (MVC) architectural pattern to separate concerns within the backend, ensuring a clean and manageable codebase.

# Database Design
The database will be simple yet secure, focusing on essential user information and utilizing document storage for flexibility and speed. Required fields include user email, contact email, hashed password, due date, and custom reminder date.

# Technology Stack Selection
Back-end Framework: Spring Boot
Database: Google Firestore
Front-End Technologies: HTML, CSS, JavaScript
Development Tools: VS Code
Version Control: GitHub

## Development Environment Setup
Ensure you have Java and Spring Boot installed for back-end development.
Set up Google Firestore for the database, following security best practices.
Use standard web technologies (HTML, CSS, JavaScript) for front-end development.
Utilize VS Code as the primary development tool, with appropriate extensions for productivity.
Manage code versions and collaboration through GitHub.

## Getting Started
To set up your development environment for the MOT Booking Timely Reminder Website, follow these steps:

1. Clone the project repository from GitHub to your local machine.
2. Ensure you have the necessary development tools and environments installed, including Java, Spring Boot, and Node.js for front-end dependencies.
3. Set up your Firestore database, ensuring you have configured the security settings appropriately.
4. Update the application.properties file with your Firestore database credentials.
5. Run the Spring Boot application locally to start the back-end server.
6. Open the project in VS Code and launch the front-end using a live server plugin or similar tool.
7 .Navigate to localhost in your browser to view the website.
   
## Contributing
We welcome contributions to the MOT Booking Timely Reminder Website project. Please read through our contributing guidelines before making a pull request. For major changes, please open an issue first to discuss what you would like to change.

Ensure to update tests as appropriate.
