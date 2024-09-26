# Pancakes

A simple Spring Boot project that fetches for the Pancake Sweats availability via REST API periodically.

Response is generated to the front end in table format and sent via email

Front end
- Thymeleaf

Backend 
- Spring MVC
- Spring Data JPA
- Spring Cloud OpenFeign

Database
- MySQL

Create the resources/application.properties file prior to deployment
```properties
spring.application.name=Pancakes
spring.mvc.static-path-pattern=/static/**

server.port=9090

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/products
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql= false

email.username = sender@email.com
email.pass = password
email.recipient = receiver@email.com
```

To create the jar file for command prompt execution
```shell
mvn clean package
```

Executing via command prompt
```shell
java -jar directory\Pancakes-0.0.1.SNAPSHOT.jar
```

To fetch all the availability
```shell
curl http://localhost:9090/
```

Timer for periodic execution can be sent via curl or the browser
```shell
curl --request POST \
  --url 'http://localhost:9090/start?duration=2' \
  --cookie cart_currency=USD
```

Stopping scheduler
```shell
curl --request POST \
  --url http://localhost:9090/stop
```