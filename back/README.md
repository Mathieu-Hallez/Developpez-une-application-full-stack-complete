# Quick Start BE

### Build Spring Boot Project with Maven
To be able to run your Spring Boot app, you will need to first build it. To build and package a Spring Boot app into a single executable Jar file with a [Maven](https://maven.apache.org/), use the below command. You will need to run it from the project folder containing the pom.xml file.
```shell
maven package
```
or you can also use
```shell
mvn install
```

### Run Spring boot app with java -jar command
To run your Spring Boot app from a command line in a Terminal window, you can use the java -jar command. This is provided your Spring Boot app was packaged as an executable jar file.
```shell
java -jar target/mdd-0.0.1-SNAPSHOT.jar
```

### Run Spring boot app using Maven
You can also use the Maven plugin to run your Spring Boot app. Use the below example to run your Spring Boot app with the Maven plugin:
```shell
mvn spring-boot:run
```

---

# Environment installation procedure

## Install MySQL

First install [MySQL](https://www.mysql.com/fr/) on your environment

### 1. Create the user
In a MySQL terminal create a user `user` with a password `123456`.
> You can create your own user with a different username and password. Just don't forget to change selected user in the `application.properties`.
```mysql
CREATE USER 'user'@'localhost' IDENTIFIED BY '123456';
```
Also grant some privileges to your new MySQL user.
```mysql
GRANT SELECT, INSERT, UPDATE, DELETE ON mdd_db.* TO 'user'@'localhost';
```

### 2. Create the database
Still in a MySQL terminal create a new database named: `mdd_db` and use it.
````mysql
CREATE DATABASE mdd_db;
````
````mysql
USE mdd_db;
````
Once the database created generate them by executing `ressources/sql/script.sql` script that you can find in `./resources/sql` :
```mysql
source ./script.sql;
```

## Java SDK

The project run in Java JDK 17. You can download [here](https://www.oracle.com/fr/java/technologies/downloads/#jdk17-windows) and install it.

## Generate a Private Key (RSA)

In the `src/main/resources/certs` directory open a terminal and execute:
````shell
openssl genpkey -algorithm RSA -out private-key.pem
````
This command generates an RSA private key and saves it to the `private-key.pem` file.
Extract the public key from the private key by running:
````shell
openssl rsa -pubout -in private-key.pem -out public-key.pem
````

If needed, you can convert it to the appropriate PCKS format and replace the old one.
````shell
openssl pkcs8 -topk8 -inform PEM -outform PEM -in private-key.pem -out private-key.pem -nocrypt
````
---

How to generate custom token Secret key with Node Js : `node -e "console.log(require('crypto').randomBytes(256).toString('base64'));"`

## Project structure

| Folder     | Utility                           |
|:-----------|:----------------------------------|
| controller | API controllers.                  |
| model      | Model to communicate with the DB. |
| service    | Processing of received data.      |
| repository | Database request access.          |
| dtos       | Data to object (DTO).             |
| configuration | All spring project configurations |


---

## Documentations

- [Swagger](http://localhost:3001/swagger-ui.html) : http://localhost:3001/swagger-ui.html
- [OpenAPI](http://localhost:3001/api-docs) : http://localhost:3001/api-docs
---

## Licensing

This project was built under the MIT licence.
