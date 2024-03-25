# P6-Full-Stack-reseau-dev


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

## NodeJs

The front part need NodeJs, download it [here](https://nodejs.org/en/download/current) and install it.
When you finish to install NodeJs don't forget to download dependencies used by the project with:
> npm install

or
> npm i

---

## Front

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 14.1.3.

Don't forget to install your node_modules before starting (`npm install`).

### Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

### Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

### Where to start

As you may have seen if you already started the app, a simple home page containing a logo, a title and a button is available. If you take a look at its code (in the `home.component.html`) you will see that an external UI library is already configured in the project.

This library is `@angular/material`, it's one of the most famous in the angular ecosystem. As you can see on their docs (https://material.angular.io/), it contains a lot of highly customizable components that will help you design your interfaces quickly.

Note: I recommend to use material however it's not mandatory, if you prefer you can get ride of it.

Good luck!
