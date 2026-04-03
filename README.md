## Booking Console Application
Description
Booking Console Application is a Java-based management system designed to handle flight reservations and passenger bookings through an interactive command-line interface. It simplifies the booking process by providing a structured workflow for searching flights, managing passenger data, and maintaining a persistent record of bookings using a domain-driven approach.

## Tech Stack
Language: Java 17+

Framework: Spring Boot (Core/Context)

Database: PostgreSQL (via Spring Data JPA)

Mapping: Custom Mapper logic (Model-to-DTO)

Testing: JUnit / Mockito

Build Tool: Maven

## Setup instructions
Clone the project:

Bash
git clone https://github.com/alievaleila/booking-console-app.git
Database Configuration: Update your PostgreSQL credentials in the application.properties file located in src/main/resources.

Build the application:

Bash
mvn clean install
Run the application:

Bash
mvn spring-boot:run
Environment variables
Ensure these variables are set for database connectivity:

Usage examples
Interactive Menu: Upon startup, the application presents a console menu where users can view available flights, search for specific destinations, and create new bookings.

Flight Management: The system handles complex domain logic for flight status and seat availability.

Persistence: All booking data is mapped from the domain layer to the database for long-term storage.
