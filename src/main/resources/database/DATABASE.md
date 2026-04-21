# Database setup

postgreSQl as database
## 0-create database manuelle
 ```
  psql -U postgres
  CREATE DATABASE memory_card_game;
 ```

## 1-connect to database 
 ```
 \c memory_card_game
 ```
## 2-create tables 
 ```
 psql -U postgres -d memory_card_game -f src/main/resources/database/db.sql
 ```
 ## 3-run mock to insert data

  ```
  psql -U postgres -d memory_card_game -f src/main/resources/database/mock.sql
  ```
## 4-add persistence.properties 
  
 add persistence.properties in src/main/resources

 ### content : 
 ```
 db.url=jdbc:postgresql://localhost:5432/memory_card_game
 db.username=postgres
 db.password=your_password
 ```