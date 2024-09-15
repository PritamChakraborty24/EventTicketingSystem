# EventTicketingSystem

The service is similar to Book My Show, that allows creating events, managing ticket inventory, and ticket purchases.

## Setup instructions

- Install Java 17 and a IDE Eclipse/IntelliJ
- Clone the repository and open it in IntelliJ and run **mvn clean install** to download the dependencies.
- Once the maven clean install is completed, **install the Lombok Plugin in your IDE (IntelliJ)** as we have used some lombok annotations in this application.
- Post that, navigate to the main class and start the application
- Once the application launches, you would be able to access the h2 database at **http://localhost:9099/h2-console/** and the swagger specs at **http://localhost:9099/swagger-ui/index.html**
- The swagger specs has been created **springdoc-openapi-starter-webmvc-ui** mvn depedency jar.
- For accessing the database, please refer to the h2 db credentials in **application.properties**.

## How to run the APIs

- Please refer to the attached Postman collection in the mail for the various APIs and how to use them for the functional requirements.

## Assumption

- The customer can buy the tickets as many as they like assuming some other service is helping them with the payment. That payment service should interact with various payment interfaces(Credit card, Debit card, UPI) and deduct the ticket amount from the customer's account. Hence, the no. of tickets to buy solely depends on the customers.

## Design Pattern

- The lombok inbuilt **@Builder** design pattern has been used.