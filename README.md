# BookStoreApi

---
#### Requirements
Implementing a Book store REST API using Spring Framework for Company XYZ.
- Frontend should be able to display a list of books which contain the Name and Author's name
- Book has fields such as: ID, ISBN, Name, Author, Categories
- Frontend should be able to show, all the details of each book.
- An admin in the XYZ company should be able to add books to the store.
- The XYZ company should have the ability to know when the book was inserted and updated in
the system.
- The API should be able to handle Unexpected scenarios and return to the clients.
---

#### Install the application

1. Make sure you have [Java](https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html) and [Maven](https://maven.apache.org) installed

2. Open the command line in the source code folder

3. Install dependencies

  ```
  $ mvn install
  ```

4. Run the project

  ```
  $ mvn spring-boot:run
  ```

5. You can change the default port in the `application.properties` file:

  ```
  server.port=5000
  ```

6. [Optional] Navigate to `http://localhost:5000/console` in your browser to access the database. You can change the default credentials in the `application.properties` file:

  ```
  spring.datasource.username=user
  spring.datasource.password=password
  ```
