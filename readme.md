# About Project
Project aim is to track order and customer balances. The project can be able to register newly created orders with information about which customer created it and what is the value of the order (the amount to be paid by customer). Additionally is can expose possibility to register payments.

It is developed in Java by using Spring-boot and Maven in Windows 10 OS. Additionally, It uses H2 as DB.

To compile project following command should be run:

```
mvn clean install
```
It will generate `assignment-0.0.1-SNAPSHOT.jar` in target folder and that jar file can be run using follwoing command:

`java -jar assignment-0.0.1-SNAPSHOT.jar`


### About End Points

Swagger API is used to provide end points and they can be reached via `http://localhost:8080/swagger-ui.html`