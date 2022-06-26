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

As a user I can view all of my incomplete todos.

As a user I can create a new todo with a description and an optional due date.

As a user I can edit a todo's description, due date, and its completed status.

As a user I can delete a todo.

As a user I can mark a todo as complete.

As a user I shouldn't be able to view, create, update, or delete another user's todos.

## Bugs

_TODO_ list the OWASP categories for each of the bugs that are in the application







## Approach

### First Pass

Start the bug hunt by manually testing the application (i.e. using the application as a user). Avoid looking at the source code. Instead, assume that the application is being hosted using a cloud service and you don't have the ability to review the source code for the backend or the frontend.

Think like a hacker. **Can you do things that you shouldn't be allowed to do?**

_TOOD_ Include or remove???

* Is authentication required for each of the "secured" API endpoints?
* Can I retrieve, create, update, or delete data that's associated with another user account?
* Can I inject HTML and JavaScript?
* Can I inject SQL statements?

_TODO_ List suggestions for extensions that make it easier to hack HTTP requests to the API

Tamper Dev (https://tamper.dev/)
Request Maker (https://chrome.google.com/webstore/detail/request-maker/kajfghlhfkcocafkcjlajldicbikpgnp?hl=en)
Live HTTP Headers (https://chrome.google.com/webstore/detail/live-http-headers/ianhploojoffmpcpilhgpacbeaifanid?hl=en)
HackBar (https://chrome.google.com/webstore/detail/hackbar/ginpbkfigcoaokgflihfhhmglmbchinc?hl=en)
Bug Magnet (https://chrome.google.com/webstore/detail/bug-magnet/efhedldbjahpgjcneebmbolkalbhckfi?hl=en)
ModHeader (https://chrome.google.com/webstore/detail/modheader/idgpnmonknjnojddfkpgkljpfnnfcklj?hl=en)







### Second Pass

After you've exhausted your ideas for manual testing, then review the code. Look for odd choices or anti-patterns. Don't worry about fixing the code, simply document the issues that you discover.
