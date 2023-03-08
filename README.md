# pizzeria
## Setup
- This project is built using Java 17. Install JDK 17 if not already. For Mac you can run `brew install openjdk@17`
- To set Java version to 17: `export JAVA_HOME=$(/usr/libexec/java_home -v17)`
- To build and run tests: `./gradlew build`
- To run Spring Boot App: `./gradlew bootRun`

## Features
- Endpoint that allows for customers to submit their email address along with the list of toppings that they would be 
  interested in. If the customer already exists then their latest favorite toppings will be recorded (previous 
  recordings will be erased, not added). 
- Endpoint that allows for the front end team to grab the list of toppings currently submitted and the number of unique 
  customers that have requested that topping. If a customer already exists.
- Endpoint which gives my favorite toppings.
- Swagger UI to show endpoints available.
- H2 console which allows the user to manage database entries.
- Metrics endpoint which displays various JVM and application metrics.

## General Info / Links
- API Docs - http://localhost:8080/swagger-ui/index.html
- H2 Console (for viewing or managing DB) - http://localhost:8080/h2-console/login.jsp
  - Login info can be viewed/set in `./src/main/resources/application.properties`
- Database table is saved in `./data/pizzeriadb`. To reset data, table can be truncated via H2 console or this file can be deleted.
- To query metrics, such as server requests or application memory used: http://localhost:8080/actuator/metrics/{metric}
  - For example:

    http://localhost:8080/actuator/metrics/http.server.requests

    http://localhost:8080/actuator/metrics/jvm.memory.used

# About Me
1. If you had to build an app from scratch, what would be your ideal tech stack?
```
I would probably build a tech stack using Java with any version >= JDK 17 since most of my professional experience has
been using Java. Kotlin and Scala are also good alternatives since both interoperate fully with Java. As far as the rest
of the tech stack, this depends on the functionality of the app:
  - Microservice: I would most likely use Spring Boot with Spring Data JDBC and a MySQL DB for data storage. Spring Boot
    allows for quick and easy app configuration and startup. Spring Data JDBC, in my opinion, gives me more control over
    the data and makes the code more readable. MySQL DB is a well established relational database management system with
    high reliability, availability and scalability.
  - ETL service: I would use a cloud compute service like AWS Lambda. The app would be a simple Java app that implements
    the Lambda function handler interface.
```
2. What makes you a great back end engineer, and why?
```
Personally I very much enjoy building and connecting different technologies and applications together to create a
complete product that satisfies certain needs and requirements. I find this part of my job and career very satisfying.
I especially enjoy the problem solving aspect that comes with it. Once your product is fully implemented and deployed,
problems will come up. One of my strengths is being able to analyze whatever data is available (via log files, 
metrics, reports, etc.) to help diagnose a problem. I'm also good at collaborating and listening to other opinions. I 
consider myself to be approachable and always willing to help whenever I can.
```