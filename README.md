# BookStoreApi

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

## Setup guide

#### Minimum Requirements

 - Java 11
 - Maven 3.x

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

5. You can change the default port (`default port is 5050`) in the `application.properties` file:

  ```
  server.port=5050
  ```

6. [Optional] Navigate to `http://localhost:5050/console` in your browser to access the database. You can change the default credentials in the `application.properties` file:

  ```
  spring.datasource.username=user
  spring.datasource.password=password
  ```
---

## API
**GET** /book - get all books (abstract book for adding to shelf later)

Example Request
```
localhost:5050/book
```


Example Response

````
[
	{
            "id": 1,
            "name": "Magna a neque",
            "price": 4656.0,
            "publishedYear": 2002,
            "category": "Action and Adventure",
            "description": "Vestibulum ut eros non enim commodo hendrerit. Donec porttitor tellus",
            "authors": [
                {
                    "id": 3,
                    "firstName": "Germane",
                    "lastName": "Mckee",
                    "birthDate": "1960-04-23",
                    "createdAt": "2020-08-07 14:12:01",
                    "updatedAt": null
                },
                {
                    "id": 1,
                    "firstName": "Erica",
                    "lastName": "French",
                    "birthDate": "1951-10-15",
                    "createdAt": "2019-09-23 13:30:52",
                    "updatedAt": "2019-04-18 19:22:38"
                },
                {
                    "id": 4,
                    "firstName": "Kylee",
                    "lastName": "Diaz",
                    "birthDate": "1977-09-19",
                    "createdAt": "2019-02-02 05:55:11",
                    "updatedAt": null
                }
            ],
            "createdAt": "2019-06-29 14:09:18",
            "updatedAt": "2019-05-12 09:52:17"
        }
]
````
---
**POST** /book - create a book (abstract book, this doesn't add book to the shelf)

````
{
    "name": "Some Book",
    "price": 1,
    "publishedYear": 2002,
    "category": "Action and Adventure",
    "description": "Vestibulum ut eros non enim commodo hendrerit. Donec porttitor tellus",
    "authors": [
        1
    ],
    "createdAt": "2019-06-29 14:09:18",
    "updatedAt": "2019-09-12 09:52:17"
}
````
Example Response

````
{
    "id": 1
}
````
---

**PATCH** /book - update a book 

````
{
    "id":1,
    "name": "Updated book",
    "price": 1,
    "publishedYear": 1995,
    "category": "Action and Adventure",
    "description": "Vestibulum ut eros non enim commodo hendrerit. Donec porttitor tellus",
    "authors": [
        1
    ],
    "createdAt": "2019-06-29 14:09:18",
    "updatedAt": "2019-09-12 09:52:17"
}
````
Example Response (No Response body)

````
HTTP 200 OK
````
---


