# Database setup

postgreSQl as database

## 1-create database named memory_card_game;
 ```
 psql -U postgres
 ```
 ```
 CREATE DATABASE  memory_card_game;
 ```
## 2-connect to database 
 ```
 \c memory_card_game
 ```
## 3-create tables 
 ```
 psql -U postgres -d memory_card_game -f src/main/resources/database/db.sql
 ```
 ## 4-run mock to insert data

  ```
  psql -U postgres -d memory_card_game -f src/main/resources/database/mock.sql
  ```
## 5-add persistence.properties 
  
 add persistence.properties in src/main/resources

 ### content : 
 ```
 db.url=jdbc:postgresql://localhost:5432/memory_card_game
 db.username=postgres
 db.password=your_password
 ```