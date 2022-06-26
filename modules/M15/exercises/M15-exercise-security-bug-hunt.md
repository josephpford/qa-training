# Exercise: Security Bug Hunt



Review KeyBank training content for level of detail and tone
Introduce bugs into the example app
Write up instructions on how to get the app up and running
Write up instructions for how to go about hunting for bugs... list the OWASP categories that the bugs fall into (and how many of each?)... suggest processes and/or tools




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

2. Set up the frontend client.

* From the command line, browse to the `client` folder and run the command `npm install` to install the React application's dependencies.
* After the dependencies have finished installing, run the command `npm start` to start the application.
* If starting the application didn't open the React application in your default browser, open a browser tab and navigate to `http://localhost:3000/`

## User Stories

_TODO_

As a user I can user my username and password to login to the application.

As a user I can view my todos.

As a user I can create a new todo.

As a user I can edit a todo.

As a user I can delete a todo.







## Bugs

_TODO_ list the OWASP categories for each of the bugs that are in the application

## Approach

Start the bug hunt by manually testing the application (i.e. using the application as a user). Avoid looking at the source code. Instead, assume that the application is being hosted using a cloud service and you don't have the ability to review the source code for the backend or the frontend. Think like a hacker. **Can you do things that you shouldn't be allowed to do?**

After you've exhausted your ideas for manual testing, then review the code. Look for odd choices or anti-patterns. Don't worry about fixing the code, simply document the issues that you discover.
