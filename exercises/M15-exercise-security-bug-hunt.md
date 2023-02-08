# Exercise: Security Bug Hunt

## Goals

Find as many security-related bugs as possible in the provided application.

## Details

Practice makes better! Explore the provided project. Use your skills and experience as a user and developer to find as many security-related bugs as possible.

## Set Up

1. [Download the project](../assets/todos.zip) and extract it.

2. Set up the backend API.

* Use the `schema-and-data.sql` script in the `server/sql` folder to build the database.
* Open the `server` project in IntelliJ.
* Update the `application.properties` file with your database server credentials or configure the necessary environment variables within IntelliJ.
* Start the application.

3. Set up the frontend client.

* From the command line, browse to the `client` folder and run the command `npm install` to install the React application's dependencies.
* After the dependencies have finished installing, run the command `npm start` to start the application.
* If starting the application didn't open the React application in your default browser, open a browser tab and navigate to `http://localhost:3000/`

## User Stories

As an existing user I can use my username and password to login to the application.

As a new user I can create a new account by providing a username and password.

As an authenticated user I can view all of my incomplete todos.

As an authenticated user I can create a new todo with a description and an optional due date.

As an authenticated user I can edit a todo's description, due date, and its completed status.

As an authenticated user I can delete a todo.

As an authenticated user I can mark a todo as complete.

As an authenticated user I shouldn't be able to view, create, update, or delete another user's todos.

As an anonymous user (i.e. unauthenticated user) I shouldn't be able to view, create, update, or delete todos.

## Bugs

The application contains at least 10 security-related bugs in the following OWASP top 10 categories:

* [A01:2021-Broken Access Control](https://owasp.org/Top10/A01_2021-Broken_Access_Control/)
* [A02:2021-Cryptographic Failures](https://owasp.org/Top10/A02_2021-Cryptographic_Failures/)
* [A03:2021-Injection](https://owasp.org/Top10/A03_2021-Injection/)
* [A04:2021-Insecure Design](https://owasp.org/Top10/A04_2021-Insecure_Design/)
* [A05:2021-Security Misconfiguration](https://owasp.org/Top10/A05_2021-Security_Misconfiguration/)
* [A07:2021-Identification and Authentication Failures](https://owasp.org/Top10/A07_2021-Identification_and_Authentication_Failures/)
* [A09:2021-Security Logging and Monitoring Failures](https://owasp.org/Top10/A09_2021-Security_Logging_and_Monitoring_Failures/)

## Approach

### First Pass

Start the bug hunt by manually testing the application (i.e. using the application as a user). Avoid looking at the source code. Instead, assume that the application is being hosted using a cloud service and you don't have the ability to review the source code for the backend or the frontend.

Think like a hacker. Use the REST Client extension in Visual Studio code to directly manipulate HTTP requests. **Can you do things that you shouldn't be allowed to do?**

### Second Pass

After you've exhausted your ideas for manual testing, then review the code. Look for odd choices or anti-patterns. Don't worry about fixing the code, simply document the issues that you discover.
