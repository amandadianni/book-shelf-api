# Bookshelf API

This is a WorkSample API for a bookshelf application.

## What this is about
It allows user to add new books to the shelf and list all books in the shelf, with pagination.

### Details
* Spring boot
* In memory database: http://www.h2database.com/html/main.html
* Swagger
* Integration tests

## Running the API

### Pre requisites
* Java >= 11 - https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html

### Running
* Inside root directory of the project, run the following command via terminal:
```shell
./gradlew bootRun
```
* The API will execute on port 8080 in your localhost
* You can consult the API documentation in the address: http://localhost:8080/bookshelf/swagger-ui/

# Coming next
* Unit Tests
* Docker
* Security
* New functionalities:
  * Creating book series
  * Filtering search
  * Ordering search