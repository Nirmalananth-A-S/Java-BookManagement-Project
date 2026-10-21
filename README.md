# Book Management System

A Java-based web application that performs complete CRUD (Create, Read, Update, Delete) operations on book records using Java Servlets, JDBC, MySQL, and HTML. This project demonstrates core Java web development concepts, database connectivity, and the DAO design pattern.

## 🚀 Features

- **Add Book:** Insert new book records into the database.
- **View Book by ID:** Retrieve a specific book using its unique ID.
- **View All Books:** Display all available book records in an HTML table.
- **Update Book:** Modify existing book information.
- **Delete Book:** Remove a book record using its ID.
- **Database Integration:** Persist book records using MySQL and JDBC.
- **Servlet-Based Request Handling:** Process HTTP requests using Jakarta Servlets.
- **DAO Pattern:** Separate database operations from servlet logic.

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java | Application logic |
| Jakarta Servlets | Handle HTTP requests and responses |
| JDBC | Database connectivity |
| MySQL | Relational database |
| HTML | User interface |
| Apache Tomcat 10.1 | Web application deployment |
| Maven | Dependency and build management |
| IntelliJ IDEA Community Edition | Development environment |

## 📂 Project Structure

```text
BookManagement/
├── pom.xml
├── src/
│   └── main/
│       ├── java/
│       │   ├── Bean/
│       │   │   └── BookBean.java
│       │   ├── BookDAO/
│       │   │   └── BookDataBaseOperation.java
│       │   ├── BookServlets/
│       │   │   ├── AddBookServlet.java
│       │   │   ├── ViewBookByIdServlet.java
│       │   │   ├── ViewAllBooksServlet.java
│       │   │   ├── UpdateBookServlet.java
│       │   │   └── DeleteBookServlet.java
│       │   └── DatabaseDetails/
│       │       └── CreateConnection.java
│       └── webapp/
│           ├── Index.html
│           ├── AddBook.html
│           └── WEB-INF/
│               └── web.xml
```

*The structure above summarizes the project organization. Adjust individual filenames if they differ from the final files in your repository.*

## 🗄️ Database Configuration

The application uses MySQL to store book information.

### Create the database

```sql
CREATE DATABASE BookManagement;
USE BookManagement;
```

### Create the book table

```sql
CREATE TABLE book (
    bookId INT PRIMARY KEY,
    bookName VARCHAR(255) NOT NULL,
    bookPrice DOUBLE NOT NULL
);
```

### Configure the database connection

Update `CreateConnection.java` with your MySQL connection URL, username, and password.

Ensure that the MySQL JDBC driver is configured in `pom.xml`.

**Security note:** Do not commit database passwords or other credentials to GitHub.

## ⚙️ Installation and Setup

### Prerequisites

- JDK
- IntelliJ IDEA Community Edition
- Apache Tomcat 10.1.x
- MySQL Server
- Maven

### Run the application

1. Clone the repository:

   ```bash
   git clone https://github.com/Nirmal32a/Java-BookManagement-Project.git
   ```

2. Open the project in IntelliJ IDEA.

3. Allow Maven to download the required dependencies.

4. Create the MySQL database and `book` table.

5. Configure your MySQL connection details.

6. Configure the project as a WAR web application and deploy it to Tomcat 10.1.

7. Start the Tomcat server.

8. Open the application:

   ```text
   http://localhost:8080/BookManagement/Index.html
   ```

## 🌐 Application Endpoints

The following servlet mappings are used for the CRUD operations.

| Operation | HTTP Method | Endpoint |
|---|---|---|
| Add Book | POST | `/BookManagement/addbook` |
| View Book by ID | GET/POST, as implemented | `/BookManagement/viewbook` |
| View All Books | GET | `/BookManagement/viewallbooks` |
| Update Book | POST | `/BookManagement/updatebook` |
| Delete Book | POST | `/BookManagement/deletebook` |

The context path is `/BookManagement`. The individual servlet mappings and HTTP methods should match the corresponding servlet annotations in the source code.

## 🔄 Application Workflow

```text
          User Interface
         (HTML Pages)
                |
                v
        Java Servlets
     (Request Handling)
                |
                v
           BookBean
      (Book Information)
                |
                v
    BookDataBaseOperation
             (DAO)
                |
                v
       CreateConnection
                |
                v
         MySQL Database
```

## 🧠 Concepts Learned

- Java classes, objects, and encapsulation
- JavaBeans
- Jakarta Servlet API
- HTTP GET and POST request handling
- Servlet URL mapping using `@WebServlet`
- HTML form processing
- JDBC connectivity and SQL operations
- Prepared statements for database queries
- DAO design pattern
- Maven project management
- Java web application deployment using Apache Tomcat
- Implementation of CRUD operations

## 🧪 Testing

The application has been developed and tested through its web interface and database operations.

- Adding a book and inserting its details into MySQL.
- Retrieving and displaying book records.
- Displaying all books in an HTML table.
- Updating existing book information.
- Deleting book records.

The View All Books functionality and successful deletion were confirmed during development.

## 🔮 Future Enhancements

- Improve the interface using CSS and responsive design.
- Add comprehensive server-side input validation.
- Display user-friendly success and error messages.
- Improve exception handling and database resource management.
- Add search, sorting, and pagination.
- Add authentication and role-based access control.
- Introduce automated tests for servlet and DAO functionality.

## 👨‍💻 Author

**Nirmalananth A S**

- GitHub: [Nirmal32a](https://github.com/Nirmal32a)
- LinkedIn: [nirmalananth-a-s-66a5a0339](https://www.linkedin.com/in/nirmalananth-a-s-66a5a0339/)

---

⭐ If you find this project useful for learning Java web development, consider starring the repository.
