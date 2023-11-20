# Movie Ticket Booking Management System 🎥

**Group:**  SE-2217
**Team members:** Nurbol Alikhan, Satygul Zhanibek, Qarsybai Baibolat


# Project Overview 👋🏻

## Idea of the Project ⚡️
The project aims to create a JavaFX application for managing movie ticket bookings. Users can register accounts, log in, and book tickets for movies. The system appears to involve user authentication, database interaction (MySQL), and a graphical user interface for a movie ticket booking platform.

## Purpose of the Work ⭐️
The primary purpose is to offer a user-friendly platform for users to manage movie ticket bookings. The system includes user authentication, database interactions with MySQL, and a visually appealing graphical user interface.

## Objectives of the Work: 💥
***1.User Authentication***: 
- Allow users to register accounts securely with valid email addresses, usernames, and passwords. 
- Authenticate users during the login process.

***2.Database Interaction:***
- Utilize MySQL for storing and retrieving user information, movie details, and booking data.
- Implement database-related operations for user registration, login, and ticket bookings.

***3.Graphical User Interface (GUI):***
- Use JavaFX to create an interactive and visually appealing GUI.
- Provide forms for user registration and login, as well as screens for selecting and booking movies.

***4.Ticket Booking Functionality:***
- Implement the logic for users to select movies, view showtimes, and book tickets.
- Display relevant information about movies and available showtimes.

***5.Error Handling and Alerts:***
- Include proper error handling, such as displaying alerts for invalid inputs or failed database operations.

***6.Design Patterns:***
- **Singleton**: Used for Database Connection Establishment, ensuring a single, shared connection throughout the application.
- **Factory Method**: Applied to create Alert instances with Alert Maker, providing a consistent way to generate alerts.
- **Repository**: Organizes data access logic, separating it from higher-level business logic.
- **MVC (Model-View-Controller)**: Divides the application into three interconnected components to manage user interface, user input, and data logic separately.
- **Chain of Responsibility**: Potentially used for handling requests in a chain, allowing multiple objects to process requests without specifying the receiver explicitly.

***7.Code Structure and Organization:***
- Ensure a well-organized and maintainable code structure, leveraging design patterns and separating concerns using classes and methods.

***8.UI/UX Considerations:***
- Pay attention to the user experience and design to make the application intuitive and user-friendly.

***9.Security Measures:***
- Implement secure practices for handling user passwords and sensitive information.



## Design patterns 🧩

***1. Singleton Pattern (Database Connection Establishment):***   1

**Purpose**: 📢 The Singleton pattern ensures that a class has only one instance and provides a global point of access to it. In your system, the Singleton pattern is applied to the database connection to guarantee that only one connection to the MySQL database is created and shared across the application.


**Implementation:** 📕 The ***_DatabaseUtil_*** class  implements the Singleton pattern to manage the database connection. This ensures that all parts of the application use the same connection instance, preventing the overhead of multiple database connections.


**Benefits:** 📈
- **Resource efficiency**: Avoids the creation of multiple database connections, saving system resources.
- **Global access**: Provides a single point of access to the database connection throughout the application.



***2. Factory Method Pattern (Alert Maker):***

**UML Diagram**:
![image](https://github.com/Andn1ght/movie-ticket-booking-management/assets/117038995/336f02f8-15d8-4347-8569-85c63b69a2b7)



**Purpose**: 📢 The Factory Method pattern defines an interface for creating an object but leaves the choice of its type to the subclasses, creating instances of classes based on certain conditions. In your system, this is employed for creating different types of alerts.


**Implementation:** 📕 The ***AlertMaker*** class  serves as a factory for creating various types of alerts, such as error messages or simple information alerts. This promotes a consistent way of displaying messages to the user.


**Benefits:** 📈
- **Flexibility:** Allows the creation of different types of alerts without specifying their exact class.
- **Consistency:** Ensures a uniform interface for creating alerts throughout the application.

***3. Repository Pattern:***

**UML Diagram**:
![image](https://github.com/Andn1ght/movie-ticket-booking-management/assets/117038995/ecc37542-554f-4a89-80cb-076eb7cf2dc9)


**Purpose**: 📢The Repository pattern separates the logic that retrieves data from the underlying s torage system, providing a clean API to the rest of the application. In your system, this is applied to manage user data and interactions with the database.


**Implementation:** 📕 For example ***UserRepository*** and ***UserRepositoryImpl*** classes  implement the Repository pattern. They encapsulate the logic for adding users, checking user existence, and signing in users, abstracting away the details of the database interaction.


**Benefits:** 📈
- **Separation of concerns:** Divides data access logic from higher-level business logic.
- **Testability:** Facilitates unit testing by isolating data access logic.


***4. Model-View-Controller (MVC) Pattern:***

**Purpose:** 📢
The MVC pattern divides the application into three interconnected components: Model, View, and Controller. This separation helps manage complexity and promotes maintainability.


**Implementation:** 📕
- **Model (e.g., User class)**:  Represents the data and business logic related to users.
- **View (e.g., FXML files for UI):** Represents the graphical user interface.
- **Controller (e.g., RegisterController)**: Handles user input, updates the model, and communicates with the view.


**Benefits:** 📈
- **Scalability:** Enables independent development of each component, promoting scalability.
- **Reusability:** Components can be reused in different parts of the application.

***5. Chain of Responsibility Pattern:***

**UML Diagram**:
![image](https://github.com/Andn1ght/movie-ticket-booking-management/assets/117038995/e8aa73cf-8d2b-45b7-8dbe-2fb60ccd8049)


**Purpose**:  📢
The Chain of Responsibility pattern passes a request along a chain of handlers. Each handler decides either to process the request or pass it to the next handler in the chain. In your system, this pattern might be used for handling specific types of requests or events.


**Implementation:** 📕
The Chain of Responsibility pattern is implemented for **input validation (BaseValidator, EmailValidator, PasswordValidator).** Validators are organized into a chain, allowing each validator to check specific conditions. If a validator can't handle the request, it passes it to the next validator in the chain.


**Benefits:** 📈
- **Decoupling:** Allows multiple objects to handle requests without needing to know the details of the entire chain.
- **Flexibility:** Enables dynamic and flexible handling of requests during runtime.


## Conclusion  📌
***Key Points of the Project:*** 🔑

- **Movie Ticket Booking Management System:** The project aims to provide a comprehensive solution for managing movie ticket bookings.
- **JavaFX Application:** The user interface is built using JavaFX, providing a modern and interactive experience.
- **MySQL Database Integration:** Utilizes MySQL for database management, ensuring data persistence.
- **Design Patterns Implementation:** Incorporates key design patterns such as Singleton, Factory Method, Repository, MVC, and Chain of Responsibility for robust architecture.


***Used Design Patterns:*** ♻️
- **Singleton:** Manages a single instance of the database connection for efficiency.
- **Factory Method:** Creates different types of alerts through a unified interface.
- **Repository:** Separates data access logic, providing a clean API for user-related operations.
- **MVC (Model-View-Controller):** Divides the application into components for scalability and maintainability.
- **Chain of Responsibility:** Potentially handles requests or events in a decoupled manner.

***Project Outcomes:***  ✅
- **Successful Database Integration:** Integration with a MySQL database for persistent storage of user and movie data.
- **User-Friendly Interface:** JavaFX provides an intuitive and visually appealing user interface.
- **Implementing Design Patterns:** Successful implementation of key design patterns contributes to the system's maintainability and extensibility.


***Challenges Faced:*** 🚨
- **Database Connectivity Issues:** Overcoming challenges related to establishing and managing the database connection.
- **Design Pattern Understanding:** Ensuring proper understanding and implementation of design patterns for an effective and maintainable system.


***Future Improvements:*** 📋
- **Enhanced Security Features:**
    Implementing additional security measures for user authentication and data protection.

- **Expanded User Features:**
    Adding features like user profiles, booking history, and personalized recommendations.

-  **Integration of External APIs:**
    Incorporating external APIs for movie details, reviews, or real-time seat availability.

- **Advanced Reporting:**
    Utilizing reporting tools or frameworks for generating detailed reports on bookings, popular movies, etc.

-  **Testing and Optimization:**
    Conducting thorough testing to identify and rectify potential issues.
    Optimizing database queries and application performance for better responsiveness.

-   **User Feedback Mechanism:**
    Implementing a feedback system to gather user opinions for continuous improvement.

##

***In conclusion, the Movie Ticket Booking Management System demonstrates successful integration of various technologies and design patterns. Overcoming challenges and implementing future improvements will further enhance the system's functionality and user experience. The project serves as a foundation for ongoing development and refinement.***
